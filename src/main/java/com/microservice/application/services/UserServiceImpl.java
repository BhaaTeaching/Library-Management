package com.microservice.application.services;

import com.microservice.application.controller.dto.request.UserRequestDto;
import com.microservice.application.exception.ValidationException;
import com.microservice.application.model.User;
import com.microservice.application.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public User addUser(UserRequestDto userRequestDto) throws ValidationException {
        boolean isUserExist = userRepository.findById(userRequestDto.getId()).isPresent();
        if (isUserExist) {
            throw new ValidationException("User already exist");
        }
        User user = new User(userRequestDto);
        return userRepository.save(user);
    }

    @Override
    public User getUserById(Long id) throws ValidationException {
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty()) {
            throw new ValidationException("User with id: " + id + " not found");
        }
        return user.get();
    }

    @Override
    public User getUserByEmailAndPassword(String email, String password) throws ValidationException {
        User user = userRepository.findByEmailAndPassword(email,password);
        if (user == null) {
            throw new ValidationException("Email or password is invalid");
        }
        return user;
    }

    @Override
    public String getPhoneNumber(User user) {
        return user.getCountryCallingCode() + user.getPhone();
    }
}
