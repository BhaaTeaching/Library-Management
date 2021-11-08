package com.microservice.application.controller;

import com.microservice.application.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.microservice.application.controller.dto.request.BookDto;

@RestController
public class BookController {

    @Autowired
    BookService bookService;

    @PostMapping("/add-book")
    public void addBook(@RequestBody BookDto bookDto) {
        bookService.addBook(bookDto);
    }

}
