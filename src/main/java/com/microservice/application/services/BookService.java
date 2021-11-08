package com.microservice.application.services;

import com.microservice.application.controller.dto.request.BookDto;

public interface BookService {
    void addBook(BookDto bookDto);
}
