package com.project.security.exception.handler;

import java.util.Date;

public class GlobalErrorMessageFields {

  private int status;

  private int code;

  private String message;

  private String developerMessage;

  /**
   * @return the status
   */
  public int getStatus() {
    return status;
  }

  /**
   * @param status the status to set
   */
  public void setStatus(int status) {
    this.status = status;
  }

  /**
   * @return the code
   */
  public int getCode() {
    return code;
  }

  /**
   * @param code the code to set
   */
  public void setCode(int code) {
    this.code = code;
  }

  /**
   * @return the message
   */
  public String getMessage() {
    return message;
  }

  /**
   * @param message the message to set
   */
  public void setMessage(String message) {
    this.message = message;
  }

  /**
   * @return the developerMessage
   */
  public String getDeveloperMessage() {
    return developerMessage;
  }

  /**
   * @param developerMessage the developerMessage to set
   */
  public void setDeveloperMessage(String developerMessage) {
    this.developerMessage = developerMessage;
  }

  /**
   * @return the date
   */
  public Date getDate() {
    return date;
  }

  /**
   * @param date the date to set
   */
  public void setDate(Date date) {
    this.date = date;
  }

  private Date date;

}
