package com.microservice.application.model;

import com.microservice.application.controller.dto.request.UserRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class User {
    @Id
    @Column
    private Long id;
    @Column
    private String name;
    @Column
    private String phone;
    @Column(columnDefinition="varchar(255) default '+972'")
    private String countryCallingCode;
    @Column
    private String email;

    public User(UserRequestDto userRequestDto) {
        this.id = userRequestDto.getId();
        this.name = userRequestDto.getName();
        this.phone = userRequestDto.getPhone();
        this.email = userRequestDto.getEmail();
    }
}
