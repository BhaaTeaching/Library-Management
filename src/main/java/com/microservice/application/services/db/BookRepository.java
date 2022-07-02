package com.microservice.application.services.db;

import com.microservice.application.controller.dto.BookDto;
import com.microservice.application.model.Book;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface BookRepository {
    List<BookDto> getAllBooks() throws SQLException;
    Optional<Book> getBookById(Long id) throws SQLException;
    List<Book> getBookByName(String name) throws SQLException;
    List<Book> getBookByAuthor(String author) throws SQLException;
    List<Book> getBookBySubject(String subject) throws SQLException;
    boolean removeBook(Long id) throws SQLException;
//    boolean editBook(Long id) throws SQLException;
//    boolean addBook(Long id) throws SQLException;
}
