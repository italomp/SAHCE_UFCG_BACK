package com.sahce.ufcg.models;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
public class Place implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @NotNull
    private String name;
    @NotNull
    @ElementCollection(targetClass= MyUser.UserType.class)
    @Enumerated(EnumType.ORDINAL) // Possibly optional (I'm not sure) but defaults to ORDINAL.
    private List<MyUser.UserType> authorizedUsers;

    public Place(){}

    public Place(String name, List<MyUser.UserType> authorizedUsers){
        this.name = name;
        this.authorizedUsers = authorizedUsers;
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

    public List<MyUser.UserType> getAuthorizedUsers() {
        return authorizedUsers;
    }

    public void setAuthorizedUsers(List<MyUser.UserType> authorizedUsers) {
        this.authorizedUsers = authorizedUsers;
    }
}
