package com.sahce.ufcg.services;

import com.sahce.ufcg.dtos.myUser.MyUserDtoRequest;
import com.sahce.ufcg.dtos.myUser.MyUserDtoResponse;
import com.sahce.ufcg.models.MyUser;
import com.sahce.ufcg.repositories.MyUserRepository;
import com.sahce.ufcg.util.PasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MyUserService {
    @Autowired
    private MyUserRepository repository;
    private PasswordEncoder passwordEncoder;

    public MyUserService() {
        passwordEncoder = new PasswordEncoder();
    }

    public MyUserDtoResponse save(MyUserDtoRequest user) throws IllegalArgumentException{
        MyUser savedUser = this.repository.save(new MyUser(
                user.getName(),
                passwordEncoder.encode(user.getPassword()),
                user.getAddress(),
                user.getEmail(),
                user.getPhone(),
                user.getUserType()));

        return new MyUserDtoResponse(
                savedUser.getId(),
                savedUser.getName(),
                savedUser.getAdress(),
                savedUser.getEmail(),
                savedUser.getPhone(),
                savedUser.getUserType());
    }
}
