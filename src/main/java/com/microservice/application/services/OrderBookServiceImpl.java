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
        if (book.getExistingCopies() > 0) {
            //TODO: let him wait for someone else cancel his order
            throw new ValidationException("The book with name: " + book.getName() + " exist in the library, location: " + book.getLocation());
        }
        if (orderRepository.countByBook(book) > book.getCopies()) {
            //TODO: let him wait for someone else cancel his order
            throw new ValidationException("Number of orders for this book with id: " + book.getId());
        }
        return orderRepository.save(new Orders(user, book));
    }
}
















