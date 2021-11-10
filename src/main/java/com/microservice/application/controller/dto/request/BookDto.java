package com.microservice.application.controller.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookDto {

    private String name;
    private String writerName;
    private Integer copies;
    private String location;
}
