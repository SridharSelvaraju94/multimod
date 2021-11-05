
package com.project.common.exception;

import org.springframework.validation.BindingResult;

public class ValidationCustomException extends CustomException {

   /**
   * serialVersionUID
   */
  private static final long serialVersionUID = 181768212859660055L;
  private BindingResult result;

  /**
   * @param result
   */
  public ValidationCustomException() {
    super();
  }

  public ValidationCustomException(BindingResult result) {
    super();
    this.result = result;
  }


  /**
   * @return the serialversionuid
   */
  public static long getSerialversionuid() {
    return serialVersionUID;
  }

  /**
   * @return the result
   */
  public BindingResult getResult() {
    return result;
  }

  /**
   * @param result the result to set
   */
  public void setResult(BindingResult result) {
    this.result = result;
  }


}
