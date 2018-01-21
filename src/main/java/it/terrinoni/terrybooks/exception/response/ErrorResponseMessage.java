package it.terrinoni.terrybooks.exception.response;

/*
 * Created by Marco Terrinoni, on 21/01/2018 - 02:59.
 */

import java.io.Serializable;

/**
 * Internal class used to deal with API error response messages.
 */
public class ErrorResponseMessage implements Serializable {

  private static final long serialVersionUID = 1L;

  private String code = null;
  private String message = null;

  public ErrorResponseMessage() {
  }

  public ErrorResponseMessage(String code, String message) {
    this.code = code;
    this.message = message;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }
}
