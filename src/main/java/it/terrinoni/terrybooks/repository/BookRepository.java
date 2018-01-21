package it.terrinoni.terrybooks.repository;

/*
 * Created by Marco Terrinoni, on 21/01/2018 - 01:56.
 */

import it.terrinoni.terrybooks.model.Book;
import java.util.Date;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * TODO
 */
public interface BookRepository extends MongoRepository<Book, String> {

  /**
   * TODO
   *
   * @param author            TBC
   * @param title             TBC
   * @param publishDateBefore TBC
   * @param publishDateAfter  TBC
   *
   * @return TBC
   */
  List<Book> findAllByAuthorAndTitleContainsAndPublishDateBeforeAndPublishDateAfter(String author,
      String title, Date publishDateBefore, Date publishDateAfter);

  /**
   * TODO
   *
   * @param author            TBC
   * @param title             TBC
   * @param publishDateBefore TBC
   *
   * @return TBC
   */
  List<Book> findAllByAuthorAndTitleContainsAndPublishDateBefore(String author, String title,
      Date publishDateBefore);

  /**
   * TODO
   *
   * @param author           TBC
   * @param title            TBC
   * @param publishDateAfter TBC
   *
   * @return TBC
   */
  List<Book> findAllByAuthorAndTitleContainsAndPublishDateAfter(String author, String title,
      Date publishDateAfter);

  /**
   * TODO
   *
   * @param author TBC
   * @param title  TBC
   *
   * @return TBC
   */
  List<Book> findAllByAuthorAndTitleContains(String author, String title);

  /**
   * TODO
   *
   * @param author            TBC
   * @param publishDateBefore TBC
   * @param publishDateAfter  TBC
   *
   * @return TBC
   */
  List<Book> findAllByAuthorAndPublishDateBeforeAndPublishDateAfter(String author,
      Date publishDateBefore, Date publishDateAfter);

  /**
   * TODO
   *
   * @param author            TBC
   * @param publishDateBefore TBC
   *
   * @return TBC
   */
  List<Book> findAllByAuthorAndPublishDateBefore(String author, Date publishDateBefore);

  /**
   * TODO
   *
   * @param author           TBC
   * @param publishDateAfter TBC
   *
   * @return TBC
   */
  List<Book> findAllByAuthorAndPublishDateAfter(String author, Date publishDateAfter);

  /**
   * TODO
   *
   * @param author TBC
   *
   * @return TBC
   */
  List<Book> findAllByAuthor(String author);

  /**
   * TODO
   *
   * @param title             TBC
   * @param publishDateBefore TBC
   * @param publishDateAfter  TBC
   *
   * @return TBC
   */
  List<Book> findAllByTitleContainsAndPublishDateBeforeAndPublishDateAfter(String title,
      Date publishDateBefore, Date publishDateAfter);

  /**
   * TODO
   *
   * @param title             TBC
   * @param publishDateBefore TBC
   *
   * @return TBC
   */
  List<Book> findAllByTitleContainsAndPublishDateBefore(String title, Date publishDateBefore);

  /**
   * TODO
   *
   * @param title            TBC
   * @param publishDateAfter TBC
   *
   * @return TBC
   */
  List<Book> findAllByTitleContainsAndPublishDateAfter(String title, Date publishDateAfter);

  /**
   * TODO
   *
   * @param title TBC
   *
   * @return TBC
   */
  List<Book> findAllByTitleContains(String title);

  /**
   * TODO
   *
   * @param publishDateBefore TBC
   * @param publishDateAfter  TBC
   *
   * @return TBC
   */
  List<Book> findAllByPublishDateBeforeAndPublishDateAfter(Date publishDateBefore,
      Date publishDateAfter);

  /**
   * TODO
   *
   * @param publishDateBefore TBC
   *
   * @return TBC
   */
  List<Book> findAllByPublishDateBefore(Date publishDateBefore);

  /**
   * TODO
   *
   * @param publishDateAfter TBC
   *
   * @return TBC
   */
  List<Book> findAllByPublishDateAfter(Date publishDateAfter);

}
