package com.microservice.application.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToOne;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class DatabaseFile {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private String docName;
    private String docType;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private Book book;
    @Lob
    private byte[] data;

    public DatabaseFile(String docName, String docType, byte[] data, Book book) {
        super();
        this.docName = docName;
        this.docType = docType;
        this.data = data;
        this.book = book;
    }
}
