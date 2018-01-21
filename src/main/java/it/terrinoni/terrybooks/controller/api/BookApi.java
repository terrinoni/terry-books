package it.terrinoni.terrybooks.controller.api;

/*
 * Created by Marco Terrinoni, on 21/01/2018 - 01:46.
 */

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import it.terrinoni.terrybooks.model.Book;
import java.util.Date;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * API interface for books; this class is implemented by the related controller.
 */
@Api(value = "book", description = "the books API")
@RequestMapping("/api/v0")
public interface BookApi {

  @ApiOperation(value = "Add a new book", notes = "This API can be used to store a new book", response = Book.class, tags = {
      "v0", "book",})
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Successful operation", response = Book.class),
      @ApiResponse(code = 500, message = "Internal server error")})
  @RequestMapping(value = "/book",
      produces = {"application/json"},
      consumes = {"application/json"},
      method = RequestMethod.POST)
  ResponseEntity<Book> addBook(
      @ApiParam(value = "Book to be added", required = true) @RequestBody Book book);

  @ApiOperation(value = "Get the single book", notes = "This API can be used to retrieve the specific book", response = Book.class, tags = {
      "v0", "book",})
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Successful operation", response = Book.class),
      @ApiResponse(code = 404, message = "Book not found"),
      @ApiResponse(code = 500, message = "Internal server error")})
  @RequestMapping(value = "/book/{isbn}",
      produces = {"application/json"},
      method = RequestMethod.GET)
  ResponseEntity<Book> getBook(
      @ApiParam(value = "ISBN of book to return", required = true) @PathVariable("isbn") String isbn);

  @ApiOperation(value = "Get all books", notes = "This API can be used to retrieve the complete collection of books", response = Book.class, responseContainer = "List", tags = {
      "v0", "book",})
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Successful operation", response = Book.class, responseContainer = "List"),
      @ApiResponse(code = 500, message = "Internal server error")})
  @RequestMapping(value = "/book",
      produces = {"application/json"},
      method = RequestMethod.GET)
  ResponseEntity<List<Book>> getBookList(
      @ApiParam(value = "Author of the book") @RequestParam(value = "author", required = false) String author,
      @ApiParam(value = "Title like") @RequestParam(value = "title_like", required = false) String titleLike,
      @ApiParam(value = "Publish date before") @RequestParam(value = "publish_date_before", required = false) Date publishDateBefore,
      @ApiParam(value = "Publish date after") @RequestParam(value = "publish_date_after", required = false) Date publishDateAfter);

  @ApiOperation(value = "Update the single book", notes = "This API can be used to modify the specific book", response = Book.class, tags = {
      "v0", "book",})
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Successful operation", response = Book.class),
      @ApiResponse(code = 404, message = "Book not found"),
      @ApiResponse(code = 500, message = "Internal server error")})
  @RequestMapping(value = "/book/{isbn}",
      produces = {"application/json"},
      consumes = {"application/json"},
      method = RequestMethod.PUT)
  ResponseEntity<Book> updateBook(
      @ApiParam(value = "ISBN of book to update", required = true) @PathVariable("isbn") String isbn,
      @ApiParam(value = "Updated book", required = true) @RequestBody Book attribute);

  @ApiOperation(value = "Delete the single book", notes = "This API can be used to delete a specific book", tags = {
      "v0", "book",})
  @ApiResponses(value = {
      @ApiResponse(code = 204, message = "Successful operation"),
      @ApiResponse(code = 500, message = "Internal server error")})
  @RequestMapping(value = "/book/{isbn}",
      produces = {"application/json"},
      method = RequestMethod.DELETE)
  ResponseEntity deleteBook(
      @ApiParam(value = "ISBN of book to delete", required = true) @PathVariable("isbn") String isbn);
}