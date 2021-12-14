package com.microservice.application.services;

import com.microservice.application.controller.dto.request.UserRequestDto;
import com.microservice.application.exception.ValidationException;
import com.microservice.application.model.User;

public interface UserService {
    User addUser(UserRequestDto userRequestDto);
    User getUserById(Long id) throws ValidationException;
    String getPhoneNumber(User user);
}
