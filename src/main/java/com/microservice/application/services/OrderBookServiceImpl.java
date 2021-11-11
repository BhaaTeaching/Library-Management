package com.microservice.application.services;

import com.microservice.application.exception.ValidationException;
import com.microservice.application.model.Book;
import com.microservice.application.model.Orders;
import com.microservice.application.model.User;
import com.microservice.application.repositories.OrderRepository;
import javassist.NotFoundException;
import org.springframework.stereotype.Service;

@Service
public class OrderBookServiceImpl implements OrderBookService {

    private final GetObjectsService getObjectsService;
    private final OrderRepository orderRepository;

    public OrderBookServiceImpl(GetObjectsService getObjectsService, OrderRepository orderRepository) {
        this.getObjectsService = getObjectsService;
        this.orderRepository = orderRepository;
    }

    @Override
    public Orders orderBook(Long bookId, Long userId) throws NotFoundException, ValidationException {
        Book book = getObjectsService.getBookById(bookId);
        User user = getObjectsService.getUserById(userId);
        //TODO: check status of the reader
        if (orderRepository.countByBook(book) > book.getCopies()) {
            throw new ValidationException("Now copies from this book with id: " + book + " try to order it.");
        }
        return orderRepository.save(new Orders(user, book));
    }
}
















