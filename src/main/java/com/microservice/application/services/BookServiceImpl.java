package com.microservice.application.services;

import com.microservice.application.repositories.BookRepository;
import com.microservice.application.controller.dto.request.BookDto;
import com.microservice.application.model.Book;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImpl implements BookService {

    BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public void addBook(BookDto bookDto) {
        Book book = new Book();
        book.setName(bookDto.getName());
        book.setWriter_name(bookDto.getWriterName());
        bookRepository.save(book);
    }
}
