package com.microservice.application.services.books;

import com.microservice.application.controller.dto.BookDto;
import com.microservice.application.model.Book;
import com.microservice.application.repositories.BookRepository;
import com.microservice.application.repositories.LoanRepository;
import javassist.NotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final LoanRepository loanRepository;

    public BookServiceImpl(BookRepository bookRepository, LoanRepository loanRepository) {
        this.bookRepository = bookRepository;
        this.loanRepository = loanRepository;
    }

    @Override
    public Book addBook(BookDto bookDto) {
        Book book = new Book(bookDto);
        return bookRepository.save(book);
    }

    @Override
    public List<BookDto> getAllBooks() {
        return ((List<Book>) bookRepository.findAll()).stream()
                .peek(book -> book.setNearestDateToReturn(loanRepository.findTopWithBookId(book.getId()))).map(BookDto::new)
                .collect(Collectors.toList());
    }

    @Override
    public BookDto getBookById(Long bookId) throws NotFoundException {
        Book book = searchBookById(bookId);
        return new BookDto(book);
    }

    @Override
    public List<BookDto> getBookByName(String name) throws NotFoundException {
        List<Book> books = bookRepository.findByName(name);
        if (books.isEmpty()) {
            throw new NotFoundException("The book: " + name + " doesn't exist, please check the Id");
        }
        return books.stream().map(BookDto::new).collect(Collectors.toList());
    }

    @Override
    public List<BookDto> getBookByAuthor(String authorName) throws NotFoundException {
        List<Book> books = bookRepository.findByAuthor(authorName);
        if (books.isEmpty()) {
            throw new NotFoundException("Book with author name: " + authorName + " doesn't exist, please check the Id");
        }
        return books.stream().map(BookDto::new).collect(Collectors.toList());
    }

    @Override
    public List<BookDto> getBookBySubject(String subject) throws NotFoundException {
        List<Book> books = bookRepository.findBySubject(subject);
        if (books.isEmpty()) {
            throw new NotFoundException("book with subject: " + subject + " doesn't exist, please check the Id");
        }
        return books.stream().map(BookDto::new).collect(Collectors.toList());
    }

    @Override
    public Book editBook(Long bookId, BookDto bookDto) throws NotFoundException {
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
    public Book removeBook(Long bookId) throws NotFoundException {
        Book book = searchBookById(bookId);
        bookRepository.delete(book);
        return book;
    }

    private Book searchBookById(Long bookId) throws NotFoundException {
        Optional<Book> book = bookRepository.findById(bookId);
        if (book.isEmpty()) {
            throw new NotFoundException("Book with id: " + bookId + " doesn't exist, please check the Id");
        }
        return book.get();
    }
}
