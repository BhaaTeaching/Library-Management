package com.microservice.application.controller.dto.response;

import com.microservice.application.model.Book;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookResponseDto {
    private Boolean isAvailable;
    private String location;

    public BookResponseDto(Book book) {
        this.isAvailable = book.getCopies() > 0;
        this.location = book.getLocation();
    }
}
