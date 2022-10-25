
package com.sahce.ufcg.dtos.myUser;

public class MyUserDtoResponse {
    private final long id;
    private final String name;
    private final String address;
    private final String email;
    private final String phone;

    public MyUserDtoResponse(long id, String name, String address, String email, String phone) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.email = email;
        this.phone = phone;
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
}

