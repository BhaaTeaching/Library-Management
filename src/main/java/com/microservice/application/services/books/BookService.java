package com.microservice.application.services.books;

import com.microservice.application.controller.dto.request.BookRequestDto;
import com.microservice.application.controller.dto.response.BookResponseDto;
import com.microservice.application.model.Book;
import javassist.NotFoundException;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.List;

public interface BookService {
    Book addBook(BookRequestDto bookRequestDto);

    List<Book> getAllBooks();

    BookResponseDto getBookById(Long bookId) throws NotFoundException;

    List<BookResponseDto> getBookByName(String name) throws NotFoundException;

    List<BookResponseDto> getBookByAuthor(String name) throws NotFoundException;

    List<BookResponseDto> getBookBySubject(String name) throws NotFoundException;

    Book editBook(Long bookId, BookRequestDto bookRequestDto) throws NotFoundException;

    String addTableOfContentFile(InputStream tableOfContentFile);

    Book removeBook(Long bookId) throws NotFoundException;
}
