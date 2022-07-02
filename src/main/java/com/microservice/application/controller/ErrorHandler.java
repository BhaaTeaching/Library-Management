package com.microservice.application.controller;

import com.microservice.application.controller.dto.response.ExceptionResponse;
import com.microservice.application.exception.ValidationException;
import javassist.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.sql.SQLException;
@Slf4j
@ControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Object> objectNotFoundException(NotFoundException notFoundException) {
        return new ResponseEntity<>(new ExceptionResponse(notFoundException.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<Object> validationException(ValidationException validationException) {
        return new ResponseEntity<>(new ExceptionResponse(validationException.getMessage()), HttpStatus.METHOD_NOT_ALLOWED);
    }

    @ExceptionHandler(SQLException.class)
    public ResponseEntity<Object> sqlExceptionException(SQLException sqlException) {
        log.error("Failed to execute sql query");
        return new ResponseEntity<>(new ExceptionResponse(sqlException.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
