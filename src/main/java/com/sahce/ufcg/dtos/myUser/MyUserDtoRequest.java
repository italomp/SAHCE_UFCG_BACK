package com.sahce.ufcg.dtos.myUser;

import com.sahce.ufcg.models.MyUser;

import java.io.Serializable;

public class MyUserDtoRequest implements Serializable {
    private final String name;
    private final String password;
    private final String address;
    private final String email;
    private final String phone;
    private final MyUser.UserType userType;
    private final byte[] documentImage;


    public MyUserDtoRequest(String name, String password, String address, String email,
                            String phone, MyUser.UserType userType, byte[] documentImage) {
        this.name = name;
        this.password = password;
        this.address = address;
        this.email = email;
        this.phone = phone;
        this.userType = userType;
        this.documentImage = documentImage;
    }
   
    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
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

    public byte[] getDocumentImage() {
        return documentImage;
    }
}
