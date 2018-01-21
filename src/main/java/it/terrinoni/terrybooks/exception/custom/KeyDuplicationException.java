package it.terrinoni.terrybooks.exception.custom;

/*
 * Created by Marco Terrinoni, on 21/01/2018 - 03:50.
 */

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Custom exception identifying storing an object with an already used identifier.
 */
@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Key already stored")
public class KeyDuplicationException extends RuntimeException {

  private static final long serialVersionUID = 1L;

  public KeyDuplicationException(String message) {
    super(message);
  }
}
