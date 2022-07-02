package com.microservice.application.services.db;

import com.microservice.application.controller.dto.BookDto;
import com.microservice.application.model.Book;
import com.microservice.application.services.db.builder.BookResultSetHandler;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Data
@Repository
public class BookRepositoryImpl implements BookRepository {

    private final Statement statement;
    private final BookResultSetHandler bookResultSetHandler;

    @Override
    public List<BookDto> getAllBooks() throws SQLException {
        String query = "SELECT * from book";
        ResultSet resultSet = statement.executeQuery(query);
        return bookResultSetHandler.buildBookDto(resultSet);
    }

    @Override
    public Optional<Book> getBookById(Long id) throws SQLException {
        String query = "SELECT * from book b where b.id='" + id +"'";
        ResultSet resultSet = statement.executeQuery(query);
        List<BookDto> bookDtos = bookResultSetHandler.buildBookDto(resultSet);
        return !bookDtos.isEmpty() ? Optional.of(new Book(bookDtos.get(0))) : Optional.empty();
    }

    @Override
    public List<Book> getBookByName(String name) throws SQLException {
        String query = "SELECT * from book b where b.name='" + name +"'";
        ResultSet resultSet = statement.executeQuery(query);
        return getBooks(resultSet);
    }

    @Override
    public List<Book> getBookByAuthor(String author) throws SQLException {
        String query = "SELECT * from book b where b.author='" + author+"'";
        ResultSet resultSet = statement.executeQuery(query);
        return getBooks(resultSet);
    }

    @Override
    public List<Book> getBookBySubject(String subject) throws SQLException {
        String query = "SELECT * from book b where b.subject='" + subject + "'";
        ResultSet resultSet = statement.executeQuery(query);
        return getBooks(resultSet);
    }

    private List<Book> getBooks(ResultSet resultSet) {
        List<BookDto> bookDtos = bookResultSetHandler.buildBookDto(resultSet);
        return !bookDtos.isEmpty() ? List.of(new Book(bookDtos.get(0))) : Collections.emptyList();
    }

    @Override
    public boolean removeBook(Long id) throws SQLException {
        String query = "DELETE FROM book WHERE id='" + id + "'";
       return statement.execute(query);
    }

//    @Override
//    public boolean editBook(Long id, BookDto bookDto) throws SQLException {
//        String query = "UPDATE book SET " "WHERE id='" + id + "'";
//        return false;
//    }
}
