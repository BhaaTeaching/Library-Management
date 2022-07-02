package com.microservice.application.services.books;

import com.microservice.application.controller.dto.BookDto;
import com.microservice.application.model.Book;
import com.microservice.application.repositories.BookRepository;
import com.microservice.application.repositories.LoanRepository;
import javassist.NotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {



    private final BookRepository bookRepository;
    private final LoanRepository loanRepository;
    private final com.microservice.application.services.db.BookRepository bookRepositoryNew;
    @Value("${is-jpa:false}")
    private Boolean isJpa;
    public BookServiceImpl(BookRepository bookRepository, LoanRepository loanRepository, com.microservice.application.services.db.BookRepository bookRepositoryNew) {
        this.bookRepository = bookRepository;
        this.loanRepository = loanRepository;
        this.bookRepositoryNew = bookRepositoryNew;
    }
    @Override
    public Book addBook(BookDto bookDto) {
        Book book = new Book(bookDto);
        return bookRepository.save(book);
    }

    @Override
    public List<BookDto> getAllBooks() throws SQLException {
        if (isJpa) {
            return ((List<com.microservice.application.model.Book>) bookRepository.findAll()).stream()
                    .peek(book -> book.setNearestDateToReturn(loanRepository.findTopWithBookId(book.getId()))).map(BookDto::new)
                    .collect(Collectors.toList());
        }
        return bookRepositoryNew.getAllBooks();

    }

    @Override
    public BookDto getBookById(Long bookId) throws NotFoundException, SQLException {
        Book book = searchBookById(bookId);
        return new BookDto(book);
    }

    @Override
    public List<BookDto> getBookByName(String name) throws NotFoundException, SQLException {
        List<Book> books = isJpa ? bookRepository.findByName(name) : bookRepositoryNew.getBookByName(name);
        if (books.isEmpty()) {
            throw new NotFoundException("The book: " + name + " doesn't exist, please check the Id");
        }
        return books.stream().map(BookDto::new).collect(Collectors.toList());
    }

    @Override
    public List<BookDto> getBookByAuthor(String authorName) throws NotFoundException, SQLException {
        List<Book> books = isJpa ? bookRepository.findByAuthor(authorName) : bookRepositoryNew.getBookByAuthor(authorName);
        if (books.isEmpty()) {
            throw new NotFoundException("Book with author name: " + authorName + " doesn't exist, please check the Id");
        }
        return books.stream().map(BookDto::new).collect(Collectors.toList());
    }

    @Override
    public List<BookDto> getBookBySubject(String subject) throws NotFoundException, SQLException {
        List<Book> books = isJpa ? bookRepository.findBySubject(subject) : bookRepositoryNew.getBookBySubject(subject);
        if (books.isEmpty()) {
            throw new NotFoundException("book with subject: " + subject + " doesn't exist, please check the Id");
        }
        return books.stream().map(BookDto::new).collect(Collectors.toList());
    }

    @Override
    public Book editBook(Long bookId, BookDto bookDto) throws NotFoundException, SQLException {
        Book book = searchBookById(bookId);
        bookDto.setId(bookId);
        BeanUtils.copyProperties(bookDto, book);
        bookRepository.save(book);
        return book;
    }

    @Override
    public String addTableOfContentFile(InputStream tableOfContentFile) {
//        String fileName = StringUtils.cleanPath(Objects.requireNonNull(tableOfContentFile.getOriginalFilename()));


        return "Saving file succeed";
    }

    @Override
    public Book removeBook(Long bookId) throws NotFoundException, SQLException {
        Book book = searchBookById(bookId);
        if (isJpa) {
            bookRepository.delete(book);
        } else {
            bookRepositoryNew.removeBook(bookId);
        }
        return book;
    }

    private Book searchBookById(Long bookId) throws NotFoundException, SQLException {
        Optional<Book> book = isJpa ? bookRepository.findById(bookId) : bookRepositoryNew.getBookById(bookId);
        if (book.isEmpty()) {
            throw new NotFoundException("Book with id: " + bookId + " doesn't exist, please check the Id");
        }
        return book.get();
    }
}
