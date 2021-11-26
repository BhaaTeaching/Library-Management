package com.microservice.application.services.books;

import com.microservice.application.exception.ValidationException;
import com.microservice.application.model.Book;
import com.microservice.application.model.Loan;
import com.microservice.application.model.User;
import com.microservice.application.repositories.BookRepository;
import com.microservice.application.repositories.LoanRepository;
import com.microservice.application.repositories.OrderRepository;
import javassist.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@Service
public class LoanBookServiceImpl implements LoanBookService {
    private final BookRepository bookRepository;
    private final LoanRepository loanRepository;
    private final GetObjectsService getObjectsService;
    private final OrderRepository orderRepository;

    public LoanBookServiceImpl(BookRepository bookRepository, LoanRepository loanRepository, GetObjectsService getObjectsService, OrderRepository orderRepository) {
        this.bookRepository = bookRepository;
        this.loanRepository = loanRepository;
        this.getObjectsService = getObjectsService;
        this.orderRepository = orderRepository;
    }

    @Override
    public Loan loan(Long bookId, Long userId) throws NotFoundException, ValidationException {
        Book book = getObjectsService.getBookById(bookId);
        User user = getObjectsService.getUserById(userId);
        int currenCopies = book.getExistingCopies();
        if (currenCopies == 0) {
            throw new ValidationException("No book to loan, try to order it.");
        }
        //TODO: check status of the reader
        book.setExistingCopies(--currenCopies);
        Book savedBook = bookRepository.save(book);
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.WEEK_OF_MONTH, 2);
        Loan loan = new Loan(user, savedBook, new Date(), new Date(calendar.getTimeInMillis()));
        return loanRepository.save(loan);

    }

    @Override
    public Loan extendLoan(Long loanId) throws NotFoundException, ValidationException {
        Loan loan = getObjectsService.getLoanById(loanId);
        if (orderRepository.countByBook(loan.getBook()) > 0) {
            throw new ValidationException("Loan id: " + loanId + " cannot be extended - this book has been ordered");
        }
        long currentReturnDate = loan.getReturnBook().getTime();
        long diff = Math.abs(currentReturnDate - (new Date()).getTime()) / (1000 * 60 * 60 * 24) % 365;
        if (diff < 7) {
            throw new ValidationException("Loan id: " + loanId + " will be expire less than 1 week, you cannot extend it.");
        }
        loan.setReturnBook(new Date(currentReturnDate + TimeUnit.DAYS.toMillis(7)));
        //TODO: Send mail to user and librarian - extend succeed
        return loanRepository.save(loan);
    }
}
