package com.microservice.application.services.books;

import com.microservice.application.model.Book;
import com.microservice.application.model.Loan;
import com.microservice.application.model.Orders;
import com.microservice.application.model.User;
import com.microservice.application.repositories.BookRepository;
import com.microservice.application.repositories.LoanRepository;
import com.microservice.application.repositories.OrderRepository;
import com.microservice.application.repositories.UserRepository;
import javassist.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GetObjectsServiceImpl implements GetObjectsService {
    private final BookRepository bookRepository;
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final LoanRepository loanRepository;

    public GetObjectsServiceImpl(BookRepository bookRepository, OrderRepository orderRepository, UserRepository userRepository, LoanRepository loanRepository) {
        this.bookRepository = bookRepository;
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.loanRepository = loanRepository;
    }

    @Override
    public Book getBookById(Long bookId) throws NotFoundException {
        Optional<Book> book = bookRepository.findById(bookId);
        if (book.isEmpty()) {
            throw new NotFoundException("No book with id: " + bookId);
        }
        return book.get();
    }

    @Override
    public Orders getOrderById(Long orderId) {
        return null;
    }

    @Override
    public Loan getLoanById(Long loanId) throws NotFoundException {
        Optional<Loan> loan = loanRepository.findById(loanId);
        if (loan.isEmpty()) {
            throw new NotFoundException("No loan with id: " + loanId);
        }
        return loan.get();
    }

    @Override
    public User getUserById(Long userId) throws NotFoundException {
        Optional<User> user = userRepository.findById(userId);
        if (user.isEmpty()) {
            throw new NotFoundException("No user with id: " + userId);
        }
        return user.get();
    }
}
