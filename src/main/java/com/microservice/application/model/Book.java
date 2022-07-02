package com.microservice.application.model;

import com.microservice.application.controller.dto.BookDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Book {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column
    private String author;
    @Column
    private Integer copies;
    @Column
    private Integer existingCopies;
    @Column
    private String location;
    @Column
    private String name;
    private Date nearestDateToReturn;

    @Column
    private String subject;

//    @OneToOne(fetch = FetchType.LAZY)
//    @JoinColumn
//    private DatabaseFile databaseFile;
    @Column
    private Long databaseFileId;
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
