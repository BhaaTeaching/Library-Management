package com.microservice.application.controller;

import com.microservice.application.controller.dto.response.ExceptionResponse;
import javassist.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Object> validationError(NotFoundException notFoundException) {
        return new ResponseEntity<>(new ExceptionResponse(notFoundException.getMessage()), HttpStatus.NOT_FOUND);
    }
}
