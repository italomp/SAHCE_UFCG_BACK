package com.sahce.ufcg.services;

import com.sahce.ufcg.dtos.myUser.MyUserDtoRequest;
import com.sahce.ufcg.dtos.myUser.MyUserDtoResponse;
import com.sahce.ufcg.dtos.myUser.MyUserDtoLogin;
import com.sahce.ufcg.exceptions.UserNotRegisteredException;
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
                savedUser.getUserType(),
                savedUser.getActive());
    }

    public MyUserDtoResponse activeUser(/*String email*/ MyUserDtoRequest userDto){
        MyUser user = repository.findByEmail(userDto.getEmail()).orElseThrow(
                () -> new UserNotRegisteredException("Não existe usuário cadastrado com esse e-mail."));
        user.setActive(true);
        MyUser savedUser = repository.save(user);
        return new MyUserDtoResponse(
                savedUser.getId(),
                savedUser.getName(),
                savedUser.getAddress(),
                savedUser.getEmail(),
                savedUser.getPhone(),
                savedUser.getUserType(),
                savedUser.getActive());
    }

    public MyUserDtoLogin login(String email, String password){
        MyUser user = repository.findByEmail(email).orElse(null);

        if (user == null)
            return null;

        if (user.getActive() && passwordEncoder.verify(password, user.getPassword()))
            return new MyUserDtoLogin(user.getEmail(), user.getPassword());

        return null;
    }
}
