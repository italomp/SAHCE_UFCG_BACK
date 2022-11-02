package com.sahce.ufcg.dtos.myUser;

import java.io.Serializable;

public class MyUserDtoLogin implements Serializable {
    private final String email;
    private final String password;

    public MyUserDtoLogin(String email, String password){
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
