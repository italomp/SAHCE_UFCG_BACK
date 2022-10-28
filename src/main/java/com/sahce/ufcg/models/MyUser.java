package com.sahce.ufcg.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import org.hibernate.usertype.UserType;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class MyUser {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @NotNull
    private String name;
    @NotNull
    @JsonIgnore
    private String password;
    private String adress;
    @NotNull
    @Column(unique = true)
    private String email;
    @NotNull
    @Column(unique = true)
    private String phone;
    //@NotNull
    private byte[] documentPicture;

    @NotNull
    private UserType userType;

    public MyUser() {
    }

    public MyUser(String name, String password, String adress, String email, String phone, UserType userType) {
        this.name = name;
        this.password = password;
        this.adress = adress;
        this.email = email;
        this.phone = phone;
        this.userType = userType;
    }

    public MyUser(String name, String password, String adress, String email, String phone, byte[] documentPicture) {
        this.name = name;
        this.password = password;
        this.adress = adress;
        this.email = email;
        this.phone = phone;
        this.documentPicture = documentPicture;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public byte[] getDocumentPicture() {
        return documentPicture;
    }

    public void setDocumentPicture(byte[] documentPicture) {
        this.documentPicture = documentPicture;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public enum UserType{
        ADMIN, EXTERNAL_USER, INTERNAL_USER
    }
}
