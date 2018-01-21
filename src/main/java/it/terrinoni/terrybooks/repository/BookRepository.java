package it.terrinoni.terrybooks.repository;

/*
 * Created by Marco Terrinoni, on 21/01/2018 - 01:56.
 */

import it.terrinoni.terrybooks.model.Book;
import java.util.Date;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Spring Data basic repository; it can be used to interact with the MongoDB instance.
 */
public interface BookRepository extends MongoRepository<Book, String> {

  /**
   * Query method to retrieve the list of books that match with the author name, book title, and
   * publish date (before and after).
   *
   * @param author            author name
   * @param title             book title
   * @param publishDateBefore publish date before
   * @param publishDateAfter  publish date after
   *
   * @return the list of books that match with the specified parameters
   */
  List<Book> findAllByAuthorAndTitleContainsAndPublishDateBeforeAndPublishDateAfter(String author,
      String title, Date publishDateBefore, Date publishDateAfter);

  /**
   * Query method to retrieve the list of books that match with the author name, book title, and
   * publish date (before).
   *
   * @param author            author name
   * @param title             book title
   * @param publishDateBefore publish date before
   *
   * @return the list of books that match with the specified parameters
   */
  List<Book> findAllByAuthorAndTitleContainsAndPublishDateBefore(String author, String title,
      Date publishDateBefore);

  /**
   * Query method to retrieve the list of books that match with the author name, book title, and
   * publish date (after).
   *
   * @param author           author name
   * @param title            book title
   * @param publishDateAfter publish date after
   *
   * @return the list of books that match with the specified parameters
   */
  List<Book> findAllByAuthorAndTitleContainsAndPublishDateAfter(String author, String title,
      Date publishDateAfter);

  /**
   * Query method to retrieve the list of books that match with the author name, and book title.
   *
   * @param author author name
   * @param title  book title
   *
   * @return the list of books that match with the specified parameters
   */
  List<Book> findAllByAuthorAndTitleContains(String author, String title);

  /**
   * Query method to retrieve the list of books that match with the author name, and publish date
   * (before and after).
   *
   * @param author            author name
   * @param publishDateBefore publish date before
   * @param publishDateAfter  publish date after
   *
   * @return the list of books that match with the specified parameters
   */
  List<Book> findAllByAuthorAndPublishDateBeforeAndPublishDateAfter(String author,
      Date publishDateBefore, Date publishDateAfter);

  /**
   * Query method to retrieve the list of books that match with the author name, and publish date
   * (before).
   *
   * @param author            author name
   * @param publishDateBefore publish date before
   *
   * @return the list of books that match with the specified parameters
   */
  List<Book> findAllByAuthorAndPublishDateBefore(String author, Date publishDateBefore);

  /**
   * Query method to retrieve the list of books that match with the author name, and publish date
   * (after).
   *
   * @param author           author name
   * @param publishDateAfter publish date after
   *
   * @return the list of books that match with the specified parameters
   */
  List<Book> findAllByAuthorAndPublishDateAfter(String author, Date publishDateAfter);

  /**
   * Query method to retrieve the list of books that match with the author name.
   *
   * @param author author name
   *
   * @return the list of books that match with the specified parameters
   */
  List<Book> findAllByAuthor(String author);

  /**
   * Query method to retrieve the list of books that match with the book title, and publish date
   * (before and after).
   *
   * @param title             book title
   * @param publishDateBefore publish date before
   * @param publishDateAfter  publish date after
   *
   * @return the list of books that match with the specified parameters
   */
  List<Book> findAllByTitleContainsAndPublishDateBeforeAndPublishDateAfter(String title,
      Date publishDateBefore, Date publishDateAfter);

  /**
   * Query method to retrieve the list of books that match with the book title, and publish date
   * (before).
   *
   * @param title             book title
   * @param publishDateBefore publish date before
   *
   * @return the list of books that match with the specified parameters
   */
  List<Book> findAllByTitleContainsAndPublishDateBefore(String title, Date publishDateBefore);

  /**
   * Query method to retrieve the list of books that match with the book title, and publish date
   * (after).
   *
   * @param title            book title
   * @param publishDateAfter publish date after
   *
   * @return the list of books that match with the specified parameters
   */
  List<Book> findAllByTitleContainsAndPublishDateAfter(String title, Date publishDateAfter);

  /**
   * Query method to retrieve the list of books that match with the book title.
   *
   * @param title book title
   *
   * @return the list of books that match with the specified parameters
   */
  List<Book> findAllByTitleContains(String title);

  /**
   * Query method to retrieve the list of books that match with the publish date (before and
   * after).
   *
   * @param publishDateBefore publish date before
   * @param publishDateAfter  publish date after
   *
   * @return the list of books that match with the specified parameters
   */
  List<Book> findAllByPublishDateBeforeAndPublishDateAfter(Date publishDateBefore,
      Date publishDateAfter);

  /**
   * Query method to retrieve the list of books that match with the publish date (before).
   *
   * @param publishDateBefore publish date before
   *
   * @return the list of books that match with the specified parameters
   */
  List<Book> findAllByPublishDateBefore(Date publishDateBefore);

  /**
   * Query method to retrieve the list of books that match with the publish date (after).
   *
   * @param publishDateAfter publish date after
   *
   * @return the list of books that match with the specified parameters
   */
  List<Book> findAllByPublishDateAfter(Date publishDateAfter);

}
