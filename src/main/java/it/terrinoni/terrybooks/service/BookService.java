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
   * @param author            author string
   * @param titleLike         title string
   * @param publishDateBefore publication date before the specified parameter
   * @param publishDateAfter  publication date after the specified parameter
   *
   * @return the list of retrieved books if exists, empty list otherwise
   */
  public List<Book> getBookList(int requestId, String author, String titleLike,
      Date publishDateBefore, Date publishDateAfter) {
    throw new UnsupportedOperationException("Not implemented");
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
    throw new UnsupportedOperationException("Not implemented");
  }

  /**
   * Service method to remove the selected book identified by the ISBN.
   *
   * @param requestId internal request identifier
   * @param isbn      book unique identifier
   */
  public void deleteBook(int requestId, String isbn) {
    throw new UnsupportedOperationException("Not implemented");
  }
}
