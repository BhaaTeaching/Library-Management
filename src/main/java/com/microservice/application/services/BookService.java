package com.microservice.application.services;

import com.microservice.application.controller.dto.request.BookDto;
import com.microservice.application.model.Book;
import javassist.NotFoundException;

public interface BookService {
    Book addBook(BookDto bookDto);

    Book getBook(Long bookId) throws NotFoundException;

    Book editBook(Long bookId, BookDto bookDto) throws NotFoundException;

    Book removeBook(Long bookId) throws NotFoundException;
}
