package com.microservice.application.services.db.builder;

import com.microservice.application.controller.dto.BookDto;

import java.sql.ResultSet;
import java.util.List;

public interface BookResultSetHandler {
    List<BookDto> buildBookDto(ResultSet result);
}
