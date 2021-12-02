package com.microservice.application.model;

import com.microservice.application.controller.dto.BookDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import java.util.Date;

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
    private Date nearestDateToReturn;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private DatabaseFile databaseFile;
//
//    @OneToOne(mappedBy = "book", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    @OnDelete(action = OnDeleteAction.CASCADE)
//    private DatabaseFile databaseFile;

    public Book(BookDto bookDto) {
        this.name = bookDto.getName();
        this.author = bookDto.getAuthor();
        this.copies = bookDto.getCopies();
        this.location = bookDto.getLocation();
        this.subject = bookDto.getSubject();
        this.existingCopies = bookDto.getExistingCopies();
    }
}
