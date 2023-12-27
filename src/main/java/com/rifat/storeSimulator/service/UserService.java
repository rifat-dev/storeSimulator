package com.rifat.storeSimulator.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.security.authentication.BadCredentialsException;
// import org.springframework.security.core.userdetails.UserDetails;
// import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
// import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.rifat.storeSimulator.DTO.NewUserDTO;
import com.rifat.storeSimulator.DTO.ResponseRegisteredUserDTO;
// import com.rifat.storeSimulator.DTO.UserLoginDTO;
import com.rifat.storeSimulator.model.User;
import com.rifat.storeSimulator.repository.UserRepository;

@Service
public class UserService {
    
    private final UserRepository userRepository;
    // private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(UUID userId) {
        return userRepository.findById(userId);
    }

    public Optional<User> getUserByLogin(String login) {
        return userRepository.findByLogin(login);
    }

    // доделать
    public ResponseRegisteredUserDTO createUser(NewUserDTO newUserDTO) {
        User user = new User();
        user.setLogin(newUserDTO.getLogin());
        // user.setHashPassword(hashPassword(newUserDTO.getPassword()));
        user.setHashPassword(newUserDTO.getPassword());
        user.setRole(newUserDTO.getRole());
        userRepository.save(user);

        ResponseRegisteredUserDTO responseRegisteredUserDTO = new ResponseRegisteredUserDTO(
            newUserDTO.getLogin(), newUserDTO.getRole()
        );
        return responseRegisteredUserDTO;
    }

    // доделать
    // public String loginUser(UserLoginDTO userLoginDTO) throws Exception {
    //     Optional<User> userOptional = userRepository.findByLogin(userLoginDTO.getLogin());

    //     if (userOptional.isPresent()) {
    //         User user = userOptional.get();
    //         if (passwordEncoder.matches(userLoginDTO.getPassword(), user.getHashPassword())) {
    //         
    //             return  "";
    //         }
    //     }
    //     throw new BadCredentialsException("Error: Check login or password");
    // }

    public void deleteUser(UUID userId) {
        userRepository.deleteById(userId);
    }

    // private static String hashPassword(String password) {
    //     return passwordEncoder.encode(password);
    // }

}
