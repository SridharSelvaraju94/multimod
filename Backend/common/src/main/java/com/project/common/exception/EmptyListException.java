
package com.project.common.exception;

public class EmptyListException extends Exception {

  /**
   *
   */
  private static final long serialVersionUID = 1L;

  private final String exceptionMessage;

  public EmptyListException(String message, String exceMessage) {
    super(message);
    this.exceptionMessage = exceMessage;
  }

  public String getExceptionMessage() {
    return exceptionMessage;
  }

  public static long getSerialversionuid() {
    return serialVersionUID;
  }

}
