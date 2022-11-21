package com.sahce.ufcg.dtos.place;

import com.sahce.ufcg.models.MyUser;

import java.io.Serializable;
import java.util.List;

public class PlaceResponseDto implements Serializable {
    private String name;
    private List<MyUser.UserType> authorizedUsers;

    public PlaceResponseDto(String name, List<MyUser.UserType> authorizedUsers) {
        this.name = name;
        this.authorizedUsers = authorizedUsers;
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
