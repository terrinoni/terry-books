package it.terrinoni.terrybooks.service;

/*
 * Created by Marco Terrinoni, on 21/01/2018 - 01:56.
 */

import com.mongodb.DuplicateKeyException;
import com.mongodb.MongoException;
import it.terrinoni.terrybooks.exception.custom.KeyDuplicationException;
import it.terrinoni.terrybooks.exception.custom.MissingIdentifierInRequestException;
import it.terrinoni.terrybooks.exception.custom.ObjectStorageException;
import it.terrinoni.terrybooks.model.Book;
import it.terrinoni.terrybooks.repository.BookRepository;
import java.util.Date;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Book service class.
 */
@Service
public class BookService {

  private static final Logger log = LoggerFactory.getLogger(BookService.class);

  @Autowired
  BookRepository bookRepository;

  /**
   * Service method to store a new book.
   *
   * @param requestId internal request identifier
   * @param book      book object to be stored
   *
   * @return the stored book
   */
  public Book addBook(int requestId, Book book) {
    log.debug("[{}] Store the new book with following data: {}", requestId, book.toString());

    Book storedBook;

    try {
      storedBook = bookRepository.insert(book);
    } catch (DuplicateKeyException dkEx) {
      String msg = String
          .format("[%d] Unable to store the specified book, the selected key already exists",
              requestId);
      log.error(msg);
      throw new KeyDuplicationException(msg);
    } catch (MongoException mEx) {
      String msg = String
          .format("[%d] Unable to store the specified book, exception: %s", requestId,
              mEx.getMessage());
      log.error(msg);
      throw new ObjectStorageException(msg, mEx);
    }

    log.info("[{}] New book storage completed with ISBN: {}", requestId, storedBook.getIsbn());

    return storedBook;
  }

  /**
   * Service method to retrieve the specific book identifier by the ISBN parameter.
   *
   * @param requestId internal request identifier
   * @param isbn      book unique identifier
   *
   * @return the retrieved book if exists, null otherwise
   */
  public Book getBook(int requestId, String isbn) {
    log.debug("[{}] Retrieve the selected book with ISBN: {}", requestId, isbn);

    // Check main identifier (this is always forced by Spring but the check is still performed for the sake of robustness)
    if (isbn == null || isbn.isEmpty()) {
      String msg = String
          .format("[%d] Missing book identifier in request", requestId);
      log.error(msg);
      throw new MissingIdentifierInRequestException(msg);
    }

    Book book;

    // Retrieve the item
    try {
      book = bookRepository.findOne(isbn);
    } catch (MongoException mEx) {
      String msg = String
          .format("[%d] Unable to retrieve the specified book, exception: %s", requestId,
              mEx.getMessage());
      log.error(msg);
      throw new RuntimeException(msg, mEx);
    }

    if (book == null) {
      log.warn("[{}] Book not found with ISBN: {}", requestId, isbn);
      return null;
    }

    log.info("[{}] Book found with ISBN: {}", requestId, book.getIsbn());

    return book;
  }

  /**
   * Service method to retrieve the list of books that match with the specified parameters.
   *
   * @param requestId         internal request identifier
   * @param author            author name
   * @param titleLike         book title
   * @param publishDateBefore publication date before the specified parameter
   * @param publishDateAfter  publication date after the specified parameter
   *
   * @return the list of retrieved books if exists, empty list otherwise
   */
  public List<Book> getBookList(int requestId, String author, String titleLike,
      Date publishDateBefore, Date publishDateAfter) {
    log.debug(
        "[{}] Retrieve the list of books that match with the following parameters: author: {}, title_like: {}, publish_date_before: {}, publish_date_after: {}",
        requestId, author, titleLike, publishDateBefore, publishDateAfter);

    List<Book> bookList = bookListQueryExecutor(requestId, author, titleLike, publishDateBefore,
        publishDateAfter);

    log.info("[{}] {} total books found", requestId, bookList.size());

    return bookList;
  }

  /**
   * Service method to update the selected book identified by the ISBN.
   *
   * @param requestId internal request identifier
   * @param isbn      book unique identifier
   * @param book      book object containing the updates to be applied
   *
   * @return the updated book
   */
  public Book updateBook(int requestId, String isbn, Book book) {
    log.debug("[{}] Update book with IBSN: {} with following data: {}", requestId, isbn,
        book.toString());

    Book updatedBook;
    try {
      updatedBook = bookRepository.save(book);
    } catch (MongoException mEx) {
      String msg = String
          .format("[%d] Unable to update the specified book, exception: %s", requestId,
              mEx.getMessage());
      log.error(msg);
      throw new RuntimeException(msg, mEx);
    }

    log.info("[{}] Book successfully update with ISBN: {}", requestId, isbn);

    return updatedBook;
  }

  /**
   * Service method to remove the selected book identified by the ISBN.
   *
   * @param requestId internal request identifier
   * @param isbn      book unique identifier
   */
  public void deleteBook(int requestId, String isbn) {
    log.debug("[{}] Remove book with ISBN: {}", requestId, isbn);

    try {
      bookRepository.delete(isbn);
    } catch (MongoException mEx) {
      String msg = String
          .format("[%d] Unable to delete the specified book, exception: %s", requestId,
              mEx.getMessage());
      log.error(msg);
      throw new RuntimeException(msg, mEx);
    }

    log.info("[{}] Book successfully deleted with ISBN: {}", requestId, isbn);
  }

  // PRIVATE INTERNAL METHODS

  /**
   * Private internal method that is used to understand which query method from book repository
   * apply to retrieve the list of books, based on the input parameters received from the
   * controller. If no parameters is specified, this method executes the "find all" query, returning
   * the whole book collection.
   *
   * @param author            author name
   * @param titleLike         book title
   * @param publishDateBefore publication date before the specified parameter
   * @param publishDateAfter  publication date after the specified parameter
   *
   * @return the list of books that match with the specified parameters
   */
  private List<Book> bookListQueryExecutor(int requestId, String author, String titleLike,
      Date publishDateBefore,
      Date publishDateAfter) {

    List<Book> bookList;

    try {
      if (author != null && !author.isEmpty()) {
        if (titleLike != null && !titleLike.isEmpty()) {
          if (publishDateBefore != null) {
            if (publishDateAfter != null) {
              // author, title, publish date before, publish date after
              bookList = bookRepository
                  .findAllByAuthorAndTitleContainsAndPublishDateBeforeAndPublishDateAfter(author,
                      titleLike, publishDateBefore, publishDateAfter);
            } else {
              // author, title, publish date before
              bookList = bookRepository
                  .findAllByAuthorAndTitleContainsAndPublishDateBefore(author, titleLike,
                      publishDateBefore);
            }
          } else {
            if (publishDateAfter != null) {
              // author, title, publish date after
              bookList = bookRepository
                  .findAllByAuthorAndTitleContainsAndPublishDateAfter(author, titleLike,
                      publishDateAfter);
            } else {
              // author, title
              bookList = bookRepository.findAllByAuthorAndTitleContains(author, titleLike);
            }
          }
        } else {
          if (publishDateBefore != null) {
            if (publishDateAfter != null) {
              // author, publish date before, publish date after
              bookList = bookRepository
                  .findAllByAuthorAndPublishDateBeforeAndPublishDateAfter(author, publishDateBefore,
                      publishDateAfter);
            } else {
              // author, publish date before
              bookList = bookRepository
                  .findAllByAuthorAndPublishDateBefore(author, publishDateBefore);
            }
          } else {
            if (publishDateAfter != null) {
              // author, publish date after
              bookList = bookRepository
                  .findAllByAuthorAndPublishDateAfter(author, publishDateAfter);
            } else {
              // author
              bookList = bookRepository.findAllByAuthor(author);
            }
          }
        }
      } else if (titleLike != null && !titleLike.isEmpty()) {
        if (publishDateBefore != null) {
          if (publishDateAfter != null) {
            // title, publish date before, publish date after
            bookList = bookRepository
                .findAllByTitleContainsAndPublishDateBeforeAndPublishDateAfter(titleLike,
                    publishDateBefore, publishDateAfter);
          } else {
            // title, publish date before
            bookList = bookRepository
                .findAllByTitleContainsAndPublishDateBefore(titleLike, publishDateBefore);
          }
        } else {
          if (publishDateAfter != null) {
            // title, publish date after
            bookList = bookRepository
                .findAllByTitleContainsAndPublishDateAfter(titleLike, publishDateAfter);
          } else {
            // title
            bookList = bookRepository.findAllByTitleContains(titleLike);
          }
        }
      } else if (publishDateBefore != null) {
        if (publishDateAfter != null) {
          // publish date before, publish date after
          bookList = bookRepository
              .findAllByPublishDateBeforeAndPublishDateAfter(publishDateBefore, publishDateAfter);
        } else {
          // publish date before
          bookList = bookRepository.findAllByPublishDateBefore(publishDateBefore);
        }
      } else if (publishDateAfter != null) {
        // publish date after
        bookList = bookRepository.findAllByPublishDateAfter(publishDateAfter);
      } else {
        bookList = bookRepository.findAll();
      }
    } catch (MongoException mEx) {
      String msg = String
          .format("[%d] Unable to retrieve the list of books, exception: %s", requestId,
              mEx.getMessage());
      log.error(msg);
      throw new RuntimeException(msg, mEx);
    }

    return bookList;
  }
}
