package com.rifat.storeSimulator.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.rifat.storeSimulator.model.User;
import com.rifat.storeSimulator.repository.UserRepository;

public class UserService {
    
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
