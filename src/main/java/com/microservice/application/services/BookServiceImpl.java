package com.microservice.application.services;

import com.microservice.application.controller.dto.request.BookDto;
import com.microservice.application.model.Book;
import com.microservice.application.repositories.BookRepository;
import javassist.NotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public Book addBook(BookDto bookDto) {
        Book book = new Book(bookDto);
        return bookRepository.save(book);
    }

    @Override
    public Book getBook(Long bookId) throws NotFoundException {
        Optional<Book> book = bookRepository.findById(bookId);
        if (book.isEmpty()) {
            throw new NotFoundException("Book with id: " + bookId + " doesn't exist, please check the Id");
        }
        return book.get();
    }

    @Override
    public Book editBook(Long bookId, BookDto bookDto) throws NotFoundException {
        Book book = getBook(bookId);
        BeanUtils.copyProperties(bookDto, book);
        bookRepository.save(book);
        return book;
    }

    @Override
    public Book removeBook(Long bookId) throws NotFoundException {
        Book book = getBook(bookId);
        bookRepository.delete(book);
        return book;
    }
}
