package com.eazydineapp.menu.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class GenericExceptionHandler extends ResponseEntityExceptionHandler {

  private final Logger log = LoggerFactory.getLogger(this.getClass());

  @ExceptionHandler(GenericServiceException.class)
  public final ResponseEntity<ErrorMessage> handleUserExceptions(Exception ex, WebRequest request) {
    ErrorMessage errorMessage = new ErrorMessage(new Date(), ex.getMessage(),
        request.getDescription(false));
    log.info("Centralized  Exception Handler..");
    return new ResponseEntity<>(errorMessage, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
  }
}