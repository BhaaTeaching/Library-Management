package com.microservice.application.services.books;

import application.ApplicationTestBase;
import com.google.gson.Gson;
import com.microservice.application.controller.dto.BookDto;
import com.microservice.application.model.Book;
import com.microservice.application.repositories.BookRepository;
import com.microservice.application.repositories.LoanRepository;
import javassist.NotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.util.AssertionErrors.assertEquals;

public class BooksServiceImplTest extends ApplicationTestBase {

    @MockBean
    private static LoanRepository loanRepository;
    @MockBean
    private static BookRepository bookRepository;
    private final static Date date = new Date();

    @Test
    public void givenAddBook_whenBookDto_thenReturnSavedBook() {
        BookService bookService = new BookServiceImpl(bookRepository, loanRepository);
        Book expected = getBook();
        when(bookRepository.save(any())).thenReturn(expected);
        Book actual = bookService.addBook(getBookDto());
        assertEquals("Test add book", new Gson().toJson(expected), new Gson().toJson(actual));
    }

    @Test
    public void givenGetAllBooks_whenBooksExist_thenReturnAllBooks() {
        BookService bookService = new BookServiceImpl(bookRepository, loanRepository);
        BookDto expected = getBookDto();
        when((List<Book>) bookRepository.findAll()).thenReturn(List.of(getBook()));
        when(loanRepository.findTopWithBookId(any())).thenReturn(date);
        List<BookDto> actual = bookService.getAllBooks();
        assertEquals("Test add book", new Gson().toJson(List.of(expected).get(0)), new Gson().toJson(actual.get(0)));
    }

    @Test
    public void givenGetBookById_whenBookId_thenReturnSavedBook() throws NotFoundException {
        BookService bookService = new BookServiceImpl(bookRepository, loanRepository);
        BookDto expected = getBookDto();
        when(bookRepository.findById(any())).thenReturn(Optional.of(getBook()));
        BookDto actual = bookService.getBookById(1035L);
        assertEquals("Test add book", new Gson().toJson(expected), new Gson().toJson(actual));
    }

    @Test
    public void givenGetBookByName_whenBookName_thenReturnSavedBook() throws NotFoundException {
        BookService bookService = new BookServiceImpl(bookRepository, loanRepository);
        List<BookDto> expected = List.of(getBookDto());
        when(bookRepository.findByName(any())).thenReturn(List.of(getBook()));
        List<BookDto> actual = bookService.getBookByName("testBook");
        assertEquals("Test add book", new Gson().toJson(expected), new Gson().toJson(actual));
    }

    @Test
    public void givenGetBookBySubject_whenBookSubject_thenReturnSavedBook() throws NotFoundException {
        BookService bookService = new BookServiceImpl(bookRepository, loanRepository);
        List<BookDto> expected = List.of(getBookDto());
        when(bookRepository.findBySubject(any())).thenReturn(List.of(getBook()));
        List<BookDto> actual = bookService.getBookBySubject("test test");
        assertEquals("Test add book", new Gson().toJson(expected), new Gson().toJson(actual));
    }

    @Test
    public void givenEditBook_whenBookIdAndBookSDto_thenReturnEditedBook() throws NotFoundException {
        BookService bookService = new BookServiceImpl(bookRepository, loanRepository);
        Book expected = getBook();
        expected.setAuthor("new author");
        when(bookRepository.findById(any())).thenReturn(Optional.of(getBook()));
        when(bookRepository.save(any())).thenReturn(getBook());
        BookDto bookDto = getBookDto();
        bookDto.setAuthor("new author");
        Book actual = bookService.editBook(1035L, (bookDto));
        assertEquals("Test add book", new Gson().toJson(expected), new Gson().toJson(actual));
    }

    @Test
    public void givenRemoveBook_whenBookId_thenReturnRemovedBook() throws NotFoundException {
        BookService bookService = new BookServiceImpl(bookRepository, loanRepository);
        Book expected = getBook();
        when(bookRepository.findById(any())).thenReturn(Optional.of(expected));
        doNothing().when(bookRepository).delete(any());
        Book actual = bookService.removeBook(1035L);
        assertEquals("Test add book", new Gson().toJson(expected), new Gson().toJson(actual));
    }
    private BookDto getBookDto() {
        return new BookDto(getBook());
    }

    private Book getBook() {
        Book book = new Book();
        book.setName("testBook");
        book.setAuthor("testAuthor");
        book.setCopies(5);
        book.setId(1035);
        book.setLocation("testLocation");
        book.setSubject("test test");
        book.setNearestDateToReturn(date);
        book.setExistingCopies(3);
        return book;
    }
}
