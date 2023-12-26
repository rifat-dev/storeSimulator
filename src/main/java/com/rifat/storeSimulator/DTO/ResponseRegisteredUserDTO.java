package com.rifat.storeSimulator.DTO;

import com.rifat.storeSimulator.model.UserRole;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseRegisteredUserDTO {

    private String login;
    private UserRole role;

    public ResponseRegisteredUserDTO(String login, UserRole role) {
        this.login = login;
        this.role = role;
    }

}
