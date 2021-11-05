package com.project.security.exception;

import com.project.common.constants.CommonConstants;
import com.project.common.exception.*;
import com.project.common.sideloading.JSONModel;
import com.project.common.sideloading.JSONModelHelper;
import com.project.security.exception.handler.CustomGenericException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

@ControllerAdvice
public class ValidationErrorHandler {
  /**
   * The message source.
   */
  @Autowired
  MessageSource messageSource;

  public ValidationErrorHandler() {
    super();
  }

  public ValidationErrorHandler(MessageSource messageSource) {
    super();
    this.messageSource = messageSource;
  }

  @ExceptionHandler(ValidationException.class)
  @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
  @ResponseBody
  public ValidationErrorsVO processValidationError(ValidationException ex) {
    BindingResult result = ex.getResult();
    List<FieldError> errors = result.getFieldErrors();
    ValidationErrorsVO errorsToReturn = processFieldErrors(errors);
    Map<String, Object> metaMap = new HashMap<>();
    metaMap.put(CommonConstants.STATUS, false);
    metaMap.put(CommonConstants.STATUS_MSG, "Please correct the below fields to proceed Further");
    metaMap.put(CommonConstants.ERRORS_COUNT, result.getErrorCount());
    errorsToReturn.setMeta(metaMap);
    return errorsToReturn;
  }

  private ValidationErrorsVO processFieldErrors(List<FieldError> errors) {
    ValidationErrorsVO dto = new ValidationErrorsVO();
    for (FieldError error : errors) {
      String errorMessage = error.getDefaultMessage();
      Map<String, String> source = new HashMap<>();
      String fieldName = CommonConstants.DATA_ATTRIBUTE + error.getField();
      source.put(CommonConstants.POINTER, fieldName);
      dto.addFieldErrors(errorMessage, source);
    }
    return dto;
  }

  @ExceptionHandler(NullPointerException.class)
  @ResponseStatus(value = HttpStatus.NOT_FOUND)
  @ResponseBody
  public JSONModel handleNullPointerException(NullPointerException e) {
    return JSONModelHelper.processJSONModelForException(e.getMessage(),
            messageSource.getMessage(e.getMessage(), null, Locale.US));
  }

  @ExceptionHandler(CustomException.class)
  @ResponseStatus(value = HttpStatus.EXPECTATION_FAILED)
  @ResponseBody
  public JSONModel handleSSMException(CustomException e) {
    return JSONModelHelper.processJSONModelForException(String.valueOf(HttpStatus.EXPECTATION_FAILED.value()),
            e.getMessage());
  }

  @ExceptionHandler(BusinessException.class)
  @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
  @ResponseBody
  public JSONModel handleSSMBusinessException(BusinessException e) {
    return JSONModelHelper.processJSONModelForException(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()),
            e.getMessage());
  }

  @ExceptionHandler(CustomGenericException.class)
  @ResponseStatus(value = HttpStatus.METHOD_NOT_ALLOWED)
  @ResponseBody
  public JSONModel handleException(CustomGenericException e) {
    return JSONModelHelper.processJSONModelForException(String.valueOf(HttpStatus.METHOD_NOT_ALLOWED.value()),
            e.getErrMsg());
  }

  @ExceptionHandler(UserLevelAuthenticatorException.class)
  @ResponseStatus(value = HttpStatus.FORBIDDEN)
  @ResponseBody
  public JSONModel handleUserLevelAuthenticatorException(UserLevelAuthenticatorException e) {
    JSONModel model = JSONModelHelper.processJSONModelForException(String.valueOf(HttpStatus.FORBIDDEN.value()),
            e.getErrormessage());
    return model;
  }

}
