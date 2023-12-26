package com.rifat.storeSimulator.DTO;

import com.rifat.storeSimulator.model.UserRole;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NewUserDTO {
    
    private String login;
    private String password;
    private UserRole role;


    public NewUserDTO(String login, String password, UserRole role) {
        this.login = login;
        this.password = password;
        this.role = role;
    }

}
