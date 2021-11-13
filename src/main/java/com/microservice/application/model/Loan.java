package com.microservice.application.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Loan {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    @JoinColumn
    private User user;
    @ManyToOne
    @JoinColumn
    private Book book;
    @Column
    private Date loanBook;
    @Column
    private Date returnBook;

    public Loan(User user, Book book, Date loanBook, Date returnBook) {
        this.user = user;
        this.book = book;
        this.loanBook = loanBook;
        this.returnBook = returnBook;
    }
}
