package com.springboot.test.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestResponseExceptionHandler extends ResponseEntityExceptionHandler {


    @ExceptionHandler({ Exception.class })
    protected ResponseEntity<Object> handleCoffeeExp(Exception ex, WebRequest request) {
        ServletWebRequest sWebRequest = ServletWebRequest.class.cast(request);
        WookieAppErrorMsg wookieAppErrorMsg = new WookieAppErrorMsg(ex, sWebRequest.getRequest().getRequestURI());
        return new ResponseEntity<>(wookieAppErrorMsg, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
