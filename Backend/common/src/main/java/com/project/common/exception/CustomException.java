
package com.project.common.exception;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class CustomException extends Exception implements Serializable {

  private static final long serialVersionUID = 1L;

  @Autowired
  private Environment env;

  /** The errorcode. */
  private String errorcode;

  /** The errormessage. */
  private String errormessage;

  /** The cause. */
  private Throwable cause;

  public CustomException() {
    super();
  }

  /**
   * @param message
   * @param cause
   */
  public CustomException(String message, Throwable cause) {
    super(message, cause);
  }

  /**
   * @param message
   */
  public CustomException(String message) {
    super(message);
  }

  /**
   * @param cause
   */
  public CustomException(Throwable cause) {
    super(cause);
  }

  /**
   * @return
   */
  public String getErrorcode() {
    return errorcode;
  }

  /**
   * @param cause
   * @param errorcode
   * @param arguments
   */
  public void setErrorcode(Throwable cause, String errorcode, String... arguments) {
    this.cause = cause;
    this.errorcode = errorcode;
    this.errormessage = String.format(env.getProperty(errorcode), (Object[]) arguments);
  }

  /**
   * @return
   */
  public String getErrormessage() {
    return errormessage;
  }

  /**
   * @param errormessage
   */
  public void setErrormessage(String errormessage) {
    this.errormessage = errormessage;
  }

  /*
   * (non-Javadoc)
   * 
   * @see java.lang.Throwable#getCause()
   */
  @Override
  public Throwable getCause() {
    return cause;
  }

  /**
   * @param cause
   */
  public void setCause(Throwable cause) {
    this.cause = cause;
  }

}
