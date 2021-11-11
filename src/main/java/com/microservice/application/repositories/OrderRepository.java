package com.microservice.application.repositories;

import com.microservice.application.model.Book;
import com.microservice.application.model.Orders;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends CrudRepository<Orders, Long> {
    long countByBook(Book book);
}
