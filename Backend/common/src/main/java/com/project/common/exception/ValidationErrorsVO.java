
package com.project.common.exception;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ValidationErrorsVO {

  private List<FieldErrorVO> errors = new ArrayList<FieldErrorVO>();

  private Map<String, Object> meta = new HashMap<String, Object>();

  /**
   * @return the fieldErrors
   */
  public List<FieldErrorVO> getErrors() {
    return errors;
  }

  /**
   * @param fieldErrors the fieldErrors to set
   */
  public void setErrors(List<FieldErrorVO> errors) {
    this.errors = errors;
  }

  public void addFieldErrors(String detail, Map<String, String> source) {
    FieldErrorVO error = new FieldErrorVO(detail, source);
    errors.add(error);
  }

  /**
   * @return the meta
   */
  public Map<String, Object> getMeta() {
    return meta;
  }

  /**
   * @param meta the meta to set
   */
  public void setMeta(Map<String, Object> meta) {
    this.meta = meta;
  }

}
