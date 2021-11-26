package com.microservice.application.services;

import com.microservice.application.controller.dto.request.UserRequestDto;
import com.microservice.application.model.User;
import com.microservice.application.repositories.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public User addUser(UserRequestDto userRequestDto) {
        User user = new User(userRequestDto);
        return userRepository.save(user);
    }

    @Override
    public String getPhoneNumber(User user) {
        return user.getCountryCallingCode() + user.getPhone();
    }
}
