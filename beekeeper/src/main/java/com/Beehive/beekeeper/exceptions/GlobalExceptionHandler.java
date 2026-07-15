package com.Beehive.beekeeper.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BaseException.class)
    public ResponseEntity<Response> handLocationNotFoundException(BaseException exception){
        return ResponseEntity.status(exception.getStatus()).body(exception.getResponse());
    }
}
