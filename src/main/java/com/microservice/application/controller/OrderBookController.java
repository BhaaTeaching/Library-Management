package com.microservice.application.controller;

import com.microservice.application.exception.ValidationException;
import com.microservice.application.services.books.OrderBookService;
import javassist.NotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
public class OrderBookController extends BaseController {
    private final OrderBookService orderBookService;

    public OrderBookController(OrderBookService orderBookService) {
        this.orderBookService = orderBookService;
    }

    @PostMapping("/order-book")
    public ResponseEntity<Object> orderBook(@RequestParam Long bookId, @RequestParam Long userId) throws ValidationException, NotFoundException {
        return responseEntity(orderBookService.orderBook(bookId, userId));
    }
}
