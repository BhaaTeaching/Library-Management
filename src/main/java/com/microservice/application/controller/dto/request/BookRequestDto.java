package com.microservice.application.controller.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookRequestDto {

    private String name;
    private String author;
    private Integer copies;
    private String location;
    private String subject;
    private Integer existingCopies;

}
