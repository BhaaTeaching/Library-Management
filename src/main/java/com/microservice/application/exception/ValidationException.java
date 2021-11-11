package com.microservice.application.exception;

public class ValidationException extends Exception {
    public ValidationException(String message) {
        super(message);
    }
}
