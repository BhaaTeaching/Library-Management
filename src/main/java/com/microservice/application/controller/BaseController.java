package com.microservice.application.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public abstract class BaseController {

    protected ResponseEntity<Object> responseEntity(Object object) {
        return new ResponseEntity<>(object, HttpStatus.OK);
    }
}
