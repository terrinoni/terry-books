package it.terrinoni.terrybooks.controller.impl;

/*
 * Created by Marco Terrinoni, on 21/01/2018 - 01:53.
 */

import io.swagger.annotations.ApiParam;
import it.terrinoni.terrybooks.controller.api.BookApi;
import it.terrinoni.terrybooks.model.Book;
import it.terrinoni.terrybooks.service.BookService;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller for Book API; this class relies on the associated book service and implements the API
 * definitions to handle book requests.
 */
@RestController
public class BookApiController implements BookApi {

  private static final Logger log = LoggerFactory.getLogger(BookApiController.class);
  private static AtomicInteger requestId = new AtomicInteger();

  @Autowired
  BookService bookService;

  /**
   * Controller method to store a new book.
   *
   * @param book book object to be stored
   *
   * @return response entity containing the stored book object
   */
  @Override
  public ResponseEntity<Book> addBook(
      @ApiParam(value = "Book to be added", required = true) @RequestBody Book book) {
    log.info("[{}] New book storage request received", requestId.incrementAndGet());

    Book res = bookService.addBook(requestId.get(), book);

    return new ResponseEntity<>(res, HttpStatus.OK);
  }

  /**
   * Controller method to retrieve a specific book by specifying its ISBN code.
   *
   * @param isbn book identifier
   *
   * @return response entity containing the retrieved book
   */
  @Override
  public ResponseEntity<Book> getBook(
      @ApiParam(value = "ISBN of book to return", required = true) @PathVariable("isbn") String isbn) {
    log.info("[{}] Book retrieval request received", requestId.incrementAndGet());

    Book res = bookService.getBook(requestId.get(), isbn);

    HttpStatus status;
    if (res == null) {
      status = HttpStatus.NOT_FOUND;
    } else {
      status = HttpStatus.OK;
    }

    return new ResponseEntity<>(res, status);
  }

  /**
   * Controller method to retrieve the list of books that match with the specified parameters.
   *
   * @param author            author string parameter
   * @param titleLike         title string parameter
   * @param publishDateBefore publication date before the specified parameter
   * @param publishDateAfter  publication date after the specified parameter
   *
   * @return response entity containing the list of the retrieved books
   */
  @Override
  public ResponseEntity<List<Book>> getBookList(
      @ApiParam(value = "Author of the book") @RequestParam(value = "author", required = false) String author,
      @ApiParam(value = "Title like") @RequestParam(value = "title_like", required = false) String titleLike,
      @ApiParam(value = "Publish date before") @RequestParam(value = "publish_date_before", required = false) Date publishDateBefore,
      @ApiParam(value = "Publish date after") @RequestParam(value = "publish_date_after", required = false) Date publishDateAfter) {
    log.info("[{}] Book list retrieval request received", requestId.incrementAndGet());

    List<Book> res = bookService
        .getBookList(requestId.get(), author, titleLike, publishDateBefore, publishDateAfter);

    return new ResponseEntity<>(res, HttpStatus.OK);
  }

  /**
   * Controller method to update a specific book.
   *
   * @param isbn book unique identifier
   * @param book book object containing the updates to be applied
   *
   * @return response entity containing the updated book
   */
  @Override
  public ResponseEntity<Book> updateBook(
      @ApiParam(value = "ISBN of book to update", required = true) @PathVariable("isbn") String isbn,
      @ApiParam(value = "Updated book", required = true) @RequestBody Book book) {
    log.info("[{}] Book update request received", requestId.incrementAndGet());

    Book res = bookService.updateBook(requestId.get(), isbn, book);

    HttpStatus status;
    if (res == null) {
      status = HttpStatus.NOT_FOUND;
    } else {
      status = HttpStatus.OK;
    }

    return new ResponseEntity<>(res, status);
  }

  /**
   * Controller method to remove a specific book
   *
   * @param isbn book unique identifier
   *
   * @return response entity containing the outcome of the delete operation
   */
  @Override
  public ResponseEntity deleteBook(
      @ApiParam(value = "ISBN of book to delete", required = true) @PathVariable("isbn") String isbn) {
    log.info("[{}] Book removal request received", requestId.incrementAndGet());

    bookService.deleteBook(requestId.get(), isbn);

    return new ResponseEntity(HttpStatus.NO_CONTENT);
  }
}