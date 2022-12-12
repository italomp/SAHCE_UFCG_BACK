package com.sahce.ufcg.dtos.myUser;

import java.io.Serializable;

public class MyUserLoginDto implements Serializable {
    private String email;
    private String password;

    public MyUserLoginDto(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public MyUserLoginDto() {
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
