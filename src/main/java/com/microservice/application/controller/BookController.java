package com.microservice.application.controller;

import com.microservice.application.controller.dto.BookDto;
import com.microservice.application.model.Book;
import com.microservice.application.services.books.BookService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import javassist.NotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.InputStream;

@RestController
@CrossOrigin(origins = "*")
public class BookController extends BaseController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping("/add-book")
    @ApiOperation(
            value = "Add book method",
            notes = "Add book using API"
    )
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Adding succeed", response = Book.class)})
    public ResponseEntity<Object> addBook(@RequestBody BookDto bookDto) {
        return responseEntity(bookService.addBook(bookDto));
    }

    @GetMapping("/get-books")
    public ResponseEntity<Object> getBooks() throws NotFoundException {
        return responseEntity(bookService.getAllBooks());
    }

    @GetMapping("/get-book/{bookId}")
    public ResponseEntity<Object> getBookById(@PathVariable Long bookId) throws NotFoundException {
        return responseEntity(bookService.getBookById(bookId));
    }

    @GetMapping("/get-book-by-name")
    public ResponseEntity<Object> getBookByName(@RequestParam String name) throws NotFoundException {
        return responseEntity(bookService.getBookByName(name));
    }

    @GetMapping("/get-book-by-author")
    public ResponseEntity<Object> getBookByAuthor(@RequestParam String author) throws NotFoundException {
        return responseEntity(bookService.getBookByAuthor(author));
    }

    @GetMapping("/get-book-by-subject")
    public ResponseEntity<Object> getBookBySubject(@RequestParam String subject) throws NotFoundException {
        return responseEntity(bookService.getBookBySubject(subject));
    }

    @PutMapping("edit-book/{bookId}")
    public ResponseEntity<Object> editDomain(@PathVariable Long bookId, @RequestBody BookDto bookDto) throws NotFoundException {
        return responseEntity(bookService.editBook(bookId, bookDto));
    }

    @PostMapping("/add-table-of-content")
    public ResponseEntity<Object> addTableOfContent(@RequestBody InputStream tableOfContentFile) {
        return responseEntity(bookService.addTableOfContentFile(tableOfContentFile));
    }

    @DeleteMapping("remove-book/{bookId}")
    public ResponseEntity<Object> removeDomain(@PathVariable Long bookId) throws NotFoundException {
        return responseEntity(bookService.removeBook(bookId));
    }
}
