package com.microservice.application.controller;

import org.apache.http.Header;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public abstract class BaseController {

    protected ResponseEntity<Object> responseEntity(Object object) {
        HttpHeaders headers = new HttpHeaders();
//        headers.set("Access-Control-Allow-Origin", "http://localhost:7070");
//        headers.set("Access-Control-Allow-Methods", "GET, POST, PATCH, PUT, DELETE, OPTIONS");
        return ResponseEntity.ok().headers(headers).body(object);
    }
}
