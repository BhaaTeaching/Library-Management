package com.microservice.application.services.books;

import com.microservice.application.controller.dto.BookDto;
import com.microservice.application.model.Book;
import javassist.NotFoundException;

import java.io.InputStream;
import java.sql.SQLException;
import java.util.List;

public interface BookService {
    Book addBook(BookDto bookDto);

    List<BookDto> getAllBooks() throws SQLException;

    BookDto getBookById(Long bookId) throws NotFoundException, SQLException;

    List<BookDto> getBookByName(String name) throws NotFoundException, SQLException;

    List<BookDto> getBookByAuthor(String name) throws NotFoundException, SQLException;

    List<BookDto> getBookBySubject(String name) throws NotFoundException, SQLException;

    Book editBook(Long bookId, BookDto bookDto) throws NotFoundException, SQLException;

    String addTableOfContentFile(InputStream tableOfContentFile);

    Book removeBook(Long bookId) throws NotFoundException, SQLException;
}
