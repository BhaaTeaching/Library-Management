package com.microservice.application.services;

import com.microservice.application.exception.ValidationException;
import javassist.NotFoundException;

public interface ReturnBookService {
    String returnBook(Long bookId) throws NotFoundException, ValidationException;
}
