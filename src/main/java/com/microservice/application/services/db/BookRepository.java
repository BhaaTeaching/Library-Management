package com.microservice.application.services.db;

import com.microservice.application.controller.dto.BookDto;

import java.sql.SQLException;
import java.util.List;

public interface BookRepository {
    List<BookDto> getAllBooks() throws SQLException;
}
