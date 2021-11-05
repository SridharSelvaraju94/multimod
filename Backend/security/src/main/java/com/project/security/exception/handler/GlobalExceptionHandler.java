package com.project.security.exception.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;


@ControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(MissingServletRequestParameterException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ResponseBody
  public GlobalErrorMessageFields handleException(MissingServletRequestParameterException e) {
    GlobalErrorMessageFields errorFields = new GlobalErrorMessageFields();
    errorFields.setStatus(HttpStatus.BAD_REQUEST.value());
    errorFields.setMessage("Servlet Request parameters are Missing");
    errorFields.setDeveloperMessage(e.getMessage());
    return errorFields;
  }

  @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
  @ExceptionHandler(HttpMessageNotReadableException.class)
  @ResponseBody
  public GlobalErrorMessageFields handleJacksonParsingException(Exception e) {
    GlobalErrorMessageFields fields = new GlobalErrorMessageFields();
    fields.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
    //fields.setMessage(messageSource.getMessage(CommonErrorCodes.ERROR_CODE_004));
    fields.setDeveloperMessage(e.getMessage());
    return fields;
  }

  @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
  @ExceptionHandler(HttpMessageNotWritableException.class)
  @ResponseBody
  public GlobalErrorMessageFields handleMessageNotWritableException(Exception e) {
    GlobalErrorMessageFields fields = new GlobalErrorMessageFields();
    fields.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
    //fields.setMessage(messageSource.getMessage(CommonErrorCodes.ERROR_CODE_004));
    fields.setDeveloperMessage(e.getMessage());
    return fields;
  }

  @ResponseStatus(value = HttpStatus.UNSUPPORTED_MEDIA_TYPE)
  @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
  @ResponseBody
  public GlobalErrorMessageFields handleUnSupportedMediaTypeException(HttpMediaTypeNotSupportedException e) {
    GlobalErrorMessageFields fields = new GlobalErrorMessageFields();
    fields.setStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE.value());
    //fields.setMessage(messageSource.getMessage(CommonErrorCodes.ERROR_CODE_004));
    fields.setDeveloperMessage(e.getMessage());
    return fields;
  }


}
