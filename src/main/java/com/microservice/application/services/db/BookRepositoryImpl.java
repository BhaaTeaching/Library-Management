package com.microservice.application.services.db;

import com.microservice.application.controller.dto.BookDto;
import com.microservice.application.services.db.builder.BookResultSetHandler;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

@AllArgsConstructor
@Data
@Repository
public class BookRepositoryImpl implements BookRepository {

    private final Statement statement;
    private final BookResultSetHandler bookResultSetHandler;

    @Override
    public List<BookDto> getAllBooks() throws SQLException {
        ResultSet resultSet = statement.executeQuery("SELECT * from book");
        return bookResultSetHandler.buildBookDto(resultSet);

    }
}
