package com.project.security.exception.handler;

public class CustomGenericException extends RuntimeException {

  private static final long serialVersionUID = 1L;

  private String errCode;
  private String errMsg;

  /**
   * @return the errCode
   */
  public String getErrCode() {
    return errCode;
  }

  /**
   * @param errCode the errCode to set
   */
  public void setErrCode(String errCode) {
    this.errCode = errCode;
  }

  /**
   * @return the errMsg
   */
  public String getErrMsg() {
    return errMsg;
  }

  /**
   * @param errMsg the errMsg to set
   */
  public void setErrMsg(String errMsg) {
    this.errMsg = errMsg;
  }

  /**
   * @param errCode
   * @param errMsg
   */
  public CustomGenericException(String errCode, String errMsg) {
    this.errCode = errCode;
    this.errMsg = errMsg;
  }

}
