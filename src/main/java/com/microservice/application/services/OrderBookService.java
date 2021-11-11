package com.microservice.application.services;

import com.microservice.application.exception.ValidationException;
import com.microservice.application.model.Orders;
import javassist.NotFoundException;

public interface OrderBookService {
    Orders orderBook(Long bookId, Long userId) throws NotFoundException, ValidationException;
}
