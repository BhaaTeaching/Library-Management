package com.microservice.application.services.db.builder;

import com.microservice.application.controller.dto.BookDto;
import com.microservice.application.model.Book;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

@Slf4j
@Service
public class BookResultSetHandlerImpl implements BookResultSetHandler {

    @Override
    public List<BookDto> buildBookDto(ResultSet resultSet) {
        List<BookDto> bookDtos = new LinkedList<>();
        try {
            while(resultSet.next()) {
                bookDtos.add(new BookDto(new Book(resultSet.getLong(1), resultSet.getString(2), resultSet.getInt(3), resultSet.getInt(4),
                        resultSet.getString(5), resultSet.getString(6), resultSet.getDate(7),
                        resultSet.getString(8), resultSet.getLong(9))));
            }
            return bookDtos;
        } catch (SQLException sqlException) {
            log.error("Failed to convert result set to Book: ", sqlException);
        }
        return Collections.emptyList();
    }
}
