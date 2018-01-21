package it.terrinoni.terrybooks.exception.custom;

/*
 * Created by Marco Terrinoni, on 21/01/2018 - 03:05.
 */

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Custom exception identifying a problem while storing a new object.
 */
@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "Unknown storage error")
public class ObjectStorageException extends RuntimeException {

  private static final long serialVersionUID = 1L;

  public ObjectStorageException(String message, Throwable cause) {
    super(message, cause);
  }

}
