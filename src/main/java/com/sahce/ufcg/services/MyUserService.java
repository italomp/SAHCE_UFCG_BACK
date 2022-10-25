package com.sahce.ufcg.services;

import com.sahce.ufcg.dtos.myUser.MyUserDtoRequest;
import com.sahce.ufcg.dtos.myUser.MyUserDtoResponse;
import com.sahce.ufcg.models.MyUser;
import com.sahce.ufcg.repositories.MyUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MyUserService {
    @Autowired
    private MyUserRepository repository;

    public MyUserService() {
    }

    public MyUserDtoResponse save(MyUserDtoRequest user) throws IllegalArgumentException{
        MyUser savedUser = this.repository.save(new MyUser(
                user.getName(),
                user.getPassword(),
                user.getAddress(),
                user.getEmail(),
                user.getPhone()));

        return new MyUserDtoResponse(
                savedUser.getId(),
                savedUser.getName(),
                savedUser.getAdress(),
                savedUser.getEmail(),
                savedUser.getPhone());
    }
}
