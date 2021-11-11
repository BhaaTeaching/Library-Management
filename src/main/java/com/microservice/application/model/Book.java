package com.microservice.application.model;

import com.microservice.application.controller.dto.request.BookRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Book {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column
    private String name;
    @Column
    private String author;
    @Column
    private String subject;
    @Column
    private Integer copies;
    @Column
    private Integer existingCopies;
    @Column
    private String location;

    public Book(BookRequestDto bookRequestDto) {
        this.name = bookRequestDto.getName();
        this.author = bookRequestDto.getAuthor();
        this.copies = bookRequestDto.getCopies();
        this.location = bookRequestDto.getLocation();
        this.subject = bookRequestDto.getSubject();
        this.existingCopies = bookRequestDto.getExistingCopies();
    }
}
