package com.microservice.application.services;

import com.microservice.application.exception.ValidationException;
import com.microservice.application.model.Book;
import com.microservice.application.repositories.BookRepository;
import javassist.NotFoundException;
import org.springframework.stereotype.Service;

@Service
public class ReturnBookServiceImpl implements ReturnBookService {
    private final GetObjectsService getObjectsService;
    private final BookRepository bookRepository;

    public ReturnBookServiceImpl(GetObjectsService getObjectsService, BookRepository bookRepository) {
        this.getObjectsService = getObjectsService;
        this.bookRepository = bookRepository;
    }

    @Override
    public String returnBook(Long bookId) throws NotFoundException, ValidationException {
        Book book = getObjectsService.getBookById(bookId);
        if (book.getExistingCopies() >= book.getCopies()) {
            throw new ValidationException("Error returning Book. Contact th support team");
        }
        book.setExistingCopies(book.getExistingCopies() + 1);
        bookRepository.save(book);
        return "Book returned successfully";
    }
}
