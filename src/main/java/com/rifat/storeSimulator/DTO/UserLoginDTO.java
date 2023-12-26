package com.rifat.storeSimulator.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserLoginDTO {
    
    private String login;
    private String password;

    public UserLoginDTO(String login, String password) {
        this.login = login;
        this.password = password;
    }

}
