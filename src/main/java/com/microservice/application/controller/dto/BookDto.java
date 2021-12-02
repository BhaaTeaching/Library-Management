package com.microservice.application.controller.dto;

import com.microservice.application.model.Book;
import com.microservice.application.model.DatabaseFile;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookDto {

    private Long id;
    private String name;
    private String author;
    private Integer copies;
    private String location;
    private String subject;
    private Integer existingCopies;
    private Boolean isAvailable;
    private Date nearestDateToReturn;
    private  DatabaseFile databaseFile;

    public BookDto(Book book) {
        this.id = book.getId();
        this.name = book.getName();
        this.author = book.getAuthor();
        this.copies = book.getCopies();
        this.subject = book.getSubject();
        this.existingCopies = book.getExistingCopies();
        this.isAvailable = book.getExistingCopies() > 0;
        this.location = book.getLocation();
        this.nearestDateToReturn = book.getNearestDateToReturn();
        this.databaseFile = book.getDatabaseFile();
    }
}
