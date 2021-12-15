package com.microservice.application.controller;

import com.microservice.application.controller.dto.request.UserRequestDto;
import com.microservice.application.exception.ValidationException;
import com.microservice.application.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
public class UserController extends BaseController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/add-user")
    public ResponseEntity<Object> addUser(@RequestBody UserRequestDto userRequestDto) {
        return responseEntity(userService.addUser(userRequestDto));
    }

    @GetMapping("/get-user/{userId}")
    public ResponseEntity<Object> getUserById(@PathVariable Long userId) throws ValidationException {
        return responseEntity(userService.getUserById(userId));
    }
}
