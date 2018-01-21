package it.terrinoni.terrybooks.exception;

/*
 * Created by Marco Terrinoni, on 21/01/2018 - 02:57.
 */

import it.terrinoni.terrybooks.exception.custom.KeyDuplicationException;
import it.terrinoni.terrybooks.exception.custom.MissingIdentifierInRequestException;
import it.terrinoni.terrybooks.exception.custom.ObjectStorageException;
import it.terrinoni.terrybooks.exception.response.ErrorResponseMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Internal class for dealing with exceptions; every exception mapped in this class can lead to an
 * HTTP error status code, in order to generate specific and self-explicative error code.
 */
@ControllerAdvice
public class TerryBooksExceptionHandler {

  // INTERNAL SERVER ERROR

  /**
   * Null pointer exception handler, it redirects to an internal server error notification.
   *
   * @param ex original exception
   *
   * @return error response message entity containing the error information
   */
  @ExceptionHandler(NullPointerException.class)
  public ResponseEntity<ErrorResponseMessage> handleNullPointerException(Exception ex) {
    ex.printStackTrace();
    return new ResponseEntity<>(new ErrorResponseMessage("0x000", ex.getMessage()),
        HttpStatus.INTERNAL_SERVER_ERROR);
  }

  /**
   * Runtime exception handler, it redirects to an internal server error notification.
   *
   * @param ex original exception
   *
   * @return error response message entity containing the error information
   */
  @ExceptionHandler(RuntimeException.class)
  public ResponseEntity<ErrorResponseMessage> handleRuntimeException(Exception ex) {
    ex.printStackTrace();
    return new ResponseEntity<>(new ErrorResponseMessage("0x001", ex.getMessage()),
        HttpStatus.INTERNAL_SERVER_ERROR);
  }

  /**
   * Object storage exception, it redirects to an internal server error notification.
   *
   * @param ex original exception
   *
   * @return error response message entity containing the error information
   */
  @ExceptionHandler(ObjectStorageException.class)
  public ResponseEntity<ErrorResponseMessage> handleBookStorageException(Exception ex) {
    ex.printStackTrace();
    return new ResponseEntity<>(new ErrorResponseMessage("0x002", ex.getMessage()),
        HttpStatus.INTERNAL_SERVER_ERROR);
  }

  // BAD REQUEST

  /**
   * Missing identifier in request exception, it redirects to a bad request notification.
   *
   * @param ex original exception
   *
   * @return error response message entity containing the error information
   */
  @ExceptionHandler(MissingIdentifierInRequestException.class)
  public ResponseEntity<ErrorResponseMessage> handleMissingIdentifierInRequestException(
      Exception ex) {
    ex.printStackTrace();
    return new ResponseEntity<>(new ErrorResponseMessage("0x003", ex.getMessage()),
        HttpStatus.BAD_REQUEST);
  }

  /**
   * Duplicated key exception, it redirects to a bad request notification.
   *
   * @param ex original exception
   *
   * @return error response message entity containing the error information
   */
  @ExceptionHandler(KeyDuplicationException.class)
  public ResponseEntity<ErrorResponseMessage> handleKeyDuplicationException(Exception ex) {
    ex.printStackTrace();
    return new ResponseEntity<>(new ErrorResponseMessage("0x004", ex.getMessage()),
        HttpStatus.BAD_REQUEST);
  }

}
