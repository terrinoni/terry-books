package it.terrinoni.terrybooks.exception.custom;

/*
 * Created by Marco Terrinoni, on 21/01/2018 - 03:27.
 */

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Custom exception identifying missing identifier in request.
 */
@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Missing identifier in request")
public class MissingIdentifierInRequestException extends RuntimeException {

  private static final long serialVersionUID = 1L;

  public MissingIdentifierInRequestException(String message) {
    super(message);
  }
}
