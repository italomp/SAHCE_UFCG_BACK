
package com.sahce.ufcg.dtos.myUser;

import com.sahce.ufcg.models.MyUser;
import com.sun.istack.NotNull;

import java.io.Serializable;

public class MyUserResponseDto implements Serializable {
    private final long id;
    private final String name;
    private final String address;
    private final String email;
    private final String phone;
    private final MyUser.UserType userType;

    public MyUserResponseDto(long id, String name, String address, String email,
                             String phone, MyUser.UserType userType) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.email = email;
        this.phone = phone;
        this.userType = userType;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public MyUser.UserType getUserType() {
        return userType;
    }

    public long getId() {
        return id;
    }
}

