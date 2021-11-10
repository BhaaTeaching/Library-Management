package com.microservice.application.controller;

import com.microservice.application.controller.dto.request.BookDto;
import com.microservice.application.services.BookService;
import javassist.NotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookController extends BaseController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping("/add-book")
    public ResponseEntity<Object> addBook(@RequestBody BookDto bookDto) {
        return responseEntity(bookService.addBook(bookDto));
    }

    @GetMapping("/get-book/{bookId}")
    public ResponseEntity<Object> getBook(@PathVariable Long bookId) throws NotFoundException {
        return responseEntity(bookService.getBook(bookId));
    }

    @PutMapping("edit-book/{bookId}")
    public ResponseEntity<Object> editDomain(@PathVariable Long bookId, @RequestBody BookDto bookDto) throws NotFoundException {
        return responseEntity(bookService.editBook(bookId, bookDto));
    }

    @DeleteMapping("remove-book/{bookId}")
    public ResponseEntity<Object> removeDomain(@PathVariable Long bookId) throws NotFoundException {
        return responseEntity(bookService.removeBook(bookId));
    }
}
