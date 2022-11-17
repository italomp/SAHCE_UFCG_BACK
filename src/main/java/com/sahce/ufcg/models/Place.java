package com.sahce.ufcg.models;

import com.sun.istack.NotNull;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
public class Place implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @NotNull
    @Column(unique = true)
    private String name;
    @NotNull
    @ElementCollection(targetClass= MyUser.UserType.class)
    @Enumerated(EnumType.ORDINAL)
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
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
