package com.microservice.application.model;

import com.microservice.application.controller.dto.request.BookRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import java.io.File;
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
//
//    @OneToOne(mappedBy = "book", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    @OnDelete(action = OnDeleteAction.CASCADE)
//    private DatabaseFile databaseFile;

    public Book(BookRequestDto bookRequestDto) {
        this.name = bookRequestDto.getName();
        this.author = bookRequestDto.getAuthor();
        this.copies = bookRequestDto.getCopies();
        this.location = bookRequestDto.getLocation();
        this.subject = bookRequestDto.getSubject();
        this.existingCopies = bookRequestDto.getExistingCopies();
    }
}
