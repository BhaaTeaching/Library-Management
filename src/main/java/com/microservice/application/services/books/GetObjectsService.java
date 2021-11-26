package com.microservice.application.services.books;

import com.microservice.application.model.Book;
import com.microservice.application.model.Loan;
import com.microservice.application.model.Orders;
import com.microservice.application.model.User;
import javassist.NotFoundException;

public interface GetObjectsService {
    Book getBookById(Long bookId) throws NotFoundException;

    Orders getOrderById(Long orderId);

    Loan getLoanById(Long loanId) throws NotFoundException;

    User getUserById(Long userId) throws NotFoundException;
}
