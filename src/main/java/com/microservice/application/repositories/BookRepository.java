package com.microservice.application.repositories;

import com.microservice.application.model.Book;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends PagingAndSortingRepository<Book, Long> {

    List<Book> findByName(String name);

    List<Book> findByAuthor(String authorName);

    List<Book> findBySubject(String authorName);

}
