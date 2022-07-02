package com.microservice.application.controller.dto;

import com.microservice.application.model.Book;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookDto {

    private Long id;
    private String author;
    private Integer copies;
    private Integer existingCopies;
    private String location;
    private String name;
    private Date nearestDateToReturn;
    private String subject;
    //  private  DatabaseFile databaseFile;
    private Long databaseFileId;
    private Boolean isAvailable;

    public BookDto(Book book) {
        this.id = book.getId();
        this.name = book.getName();
        this.author = book.getAuthor();
        this.copies = book.getCopies();
        this.subject = book.getSubject();
        this.existingCopies = book.getExistingCopies();
        this.isAvailable = book.getExistingCopies() != null && book.getExistingCopies() > 0;
        this.location = book.getLocation();
        this.nearestDateToReturn = book.getNearestDateToReturn();
        this.databaseFileId = book.getDatabaseFileId();
    }
}
