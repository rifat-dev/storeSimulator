package com.rifat.storeSimulator.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rifat.storeSimulator.DTO.NewUserDTO;
import com.rifat.storeSimulator.DTO.ResponseRegisteredUserDTO;
import com.rifat.storeSimulator.service.UserService;

@RestController
@RequestMapping("api/user")
public class UserController {
    
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<ResponseRegisteredUserDTO> registerUser(@RequestBody NewUserDTO newUserDTO) {
        ResponseRegisteredUserDTO createdUser = userService.createUser(newUserDTO);
        return ResponseEntity.ok(createdUser);
    }

}
