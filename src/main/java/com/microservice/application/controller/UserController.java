package com.microservice.application.controller;

import com.microservice.application.controller.dto.request.UserRequestDto;
import com.microservice.application.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController extends BaseController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/add-user")
    public ResponseEntity<Object> addUser(@RequestBody UserRequestDto userRequestDto) {
        return responseEntity(userService.addUser(userRequestDto));
    }
}
