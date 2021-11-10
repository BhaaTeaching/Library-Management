package com.microservice.application.model;

import com.microservice.application.controller.dto.request.BookDto;
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
    private String writer_name;
    @Column
    private Integer copies;
    @Column
    private String location;

    public Book(BookDto bookDto) {
        this.name = bookDto.getName();
        this.writer_name = bookDto.getWriterName();
        this.copies = bookDto.getCopies();
        this.location = bookDto.getLocation();
    }
}
