package com.microservice.application.controller;

import com.microservice.application.exception.ValidationException;
import com.microservice.application.services.books.ReturnBookService;
import javassist.NotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
public class ReturnBookController extends BaseController {
    private final ReturnBookService returnBookService;

    public ReturnBookController(ReturnBookService returnBookService) {
        this.returnBookService = returnBookService;
    }

    @PostMapping("return-book")
    public ResponseEntity<Object> returnBook(@RequestParam Long bookId) throws NotFoundException, ValidationException {
        return responseEntity(returnBookService.returnBook(bookId));
    }
}
