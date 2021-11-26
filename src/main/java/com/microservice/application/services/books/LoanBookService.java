package com.microservice.application.services.books;

import com.microservice.application.exception.ValidationException;
import com.microservice.application.model.Loan;
import javassist.NotFoundException;

public interface LoanBookService {
    Loan loan(Long bookId, Long userId) throws NotFoundException, ValidationException;

    Loan extendLoan(Long loanId) throws NotFoundException, ValidationException;
}
