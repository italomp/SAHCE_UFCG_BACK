package com.sahce.ufcg.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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
    private String address;
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
    @NotNull
    private Boolean active;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "owner", orphanRemoval = true)
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private List<Schedule> schedules;

    public MyUser() {
    }

    public MyUser(String name, String password, String address, String email,
                  String phone, UserType userType, Boolean active) {
        this.name = name;
        this.password = password;
        this.address = address;
        this.email = email;
        this.phone = phone;
        this.userType = userType;
        this.active = active;
        this.schedules = new ArrayList<>();
    }

    public MyUser(String name, String password, String address, String email, String phone, byte[] documentPicture) {
        this.name = name;
        this.password = password;
        this.address = address;
        this.email = email;
        this.phone = phone;
        this.documentPicture = documentPicture;
        this.schedules = new ArrayList<>();
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public List<Schedule> getSchedules() {
        return schedules;
    }

    public void setSchedules(List<Schedule> schedules) {
        this.schedules = schedules;
    }

    @Override
    public String toString(){
        return
                "id: " + this.id + " name: " + this.name + " email" +  this.email + " address" +
                this.address + " phone" +  this.phone + " password: " +   this.password + " userType: " +
                this.userType + " status:" + this.active;
    }

    public enum UserType{
        ADMIN, EXTERNAL_USER, INTERNAL_USER
    }
}
