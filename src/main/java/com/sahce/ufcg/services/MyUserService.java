package com.sahce.ufcg.services;

import com.sahce.ufcg.dtos.myUser.MyUserDtoRequest;
import com.sahce.ufcg.dtos.myUser.MyUserDtoResponse;
import com.sahce.ufcg.exceptions.UserNotRegisteredException;
import com.sahce.ufcg.models.MyUser;
import com.sahce.ufcg.repositories.MyUserRepository;
import com.sahce.ufcg.util.PasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MyUserService {
    @Autowired
    private MyUserRepository repository;
    private PasswordEncoder passwordEncoder;

    public MyUserService() {
        passwordEncoder = new PasswordEncoder();
    }

    public MyUserDtoResponse save(MyUserDtoRequest user) throws IllegalArgumentException{
        Boolean activeStatus = false;
        MyUser savedUser = this.repository.save(new MyUser(
                user.getName(),
                passwordEncoder.encode(user.getPassword()),
                user.getAddress(),
                user.getEmail(),
                user.getPhone(),
                user.getUserType(),
                activeStatus));

        return new MyUserDtoResponse(
                savedUser.getId(),
                savedUser.getName(),
                savedUser.getAddress(),
                savedUser.getEmail(),
                savedUser.getPhone(),
                savedUser.getUserType());
    }

    public MyUserDtoResponse activeUser(/*String email*/ MyUserDtoRequest userDto){
        MyUser user, savedUser;
        user = repository.findByEmail(userDto.getEmail()).orElseThrow(
                () -> new UserNotRegisteredException("Não existe usuário cadastrado com esse e-mail."));
        user.setActive(true);
        savedUser = repository.save(user);
        return new MyUserDtoResponse(
                savedUser.getId(),
                savedUser.getName(),
                savedUser.getAddress(),
                savedUser.getEmail(),
                savedUser.getPhone(),
                savedUser.getUserType());
    }

    public MyUserDtoResponse getUserByEmail(String email){
        MyUser user = repository.findByEmail(email).orElseThrow(
                () -> new UserNotRegisteredException("Não existe usuário cadastrado com esse e-mail."));

        return new MyUserDtoResponse(
                user.getId(),
                user.getName(),
                user.getAddress(),
                user.getEmail(),
                user.getPhone(),
                user.getUserType());
    }
}
