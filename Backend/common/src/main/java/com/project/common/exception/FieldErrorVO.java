
package com.project.common.exception;

import java.util.Map;

public class FieldErrorVO {

  private String detail;

  private Map<String, String> source;

  /**
   * @param detail
   * @param source
   */
  public FieldErrorVO(String detail, Map<String, String> source) {
    this.detail = detail;
    this.source = source;
  }

  /**
   * @return the detail
   */
  public String getDetail() {
    return detail;
  }

  /**
   * @param detail the detail to set
   */
  public void setDetail(String detail) {
    this.detail = detail;
  }

  /**
   * @return the source
   */
  public Map<String, String> getSource() {
    return source;
  }

  /**
   * @param source the source to set
   */
  public void setSource(Map<String, String> source) {
    this.source = source;
  }

}
