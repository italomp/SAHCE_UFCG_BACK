package com.sahce.ufcg.services;

import com.sahce.ufcg.dtos.myUser.MyUserDtoRequest;
import com.sahce.ufcg.dtos.myUser.MyUserResponseDto;
import com.sahce.ufcg.exceptions.InactiveUserNotFoundException;
import com.sahce.ufcg.exceptions.UserNotRegisteredException;
import com.sahce.ufcg.models.MyUser;
import com.sahce.ufcg.repositories.MyUserRepository;
import com.sahce.ufcg.util.PasswordEncoder;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MyUserService {
    @Autowired
    private MyUserRepository repository;
    private PasswordEncoder passwordEncoder;

    public MyUserService() {
        passwordEncoder = new PasswordEncoder();
    }

    public MyUserResponseDto save(MyUserDtoRequest user) throws IllegalArgumentException{
        Boolean activeStatus = false;
        MyUser savedUser = this.repository.save(new MyUser(
                user.getName(),
                passwordEncoder.encode(user.getPassword()),
                user.getAddress(),
                user.getEmail(),
                user.getPhone(),
                user.getUserType(),
                activeStatus,
                user.getDocumentPicture()));

        return new MyUserResponseDto(
                savedUser.getId(),
                savedUser.getName(),
                savedUser.getAddress(),
                savedUser.getEmail(),
                savedUser.getPhone(),
                savedUser.getUserType());
    }

    public MyUserResponseDto activeUser(/*String email*/ MyUserDtoRequest userDto){
        MyUser user, savedUser;
        user = repository.findByEmail(userDto.getEmail()).orElseThrow(
                () -> new UserNotRegisteredException("Não existe usuário cadastrado com esse e-mail."));
        user.setActive(true);
        savedUser = repository.save(user);
        return new MyUserResponseDto(
                savedUser.getId(),
                savedUser.getName(),
                savedUser.getAddress(),
                savedUser.getEmail(),
                savedUser.getPhone(),
                savedUser.getUserType());
    }

    public MyUserResponseDto getUserByEmail(String email){
        MyUser user = repository.findByEmail(email).orElseThrow(
                () -> new UserNotRegisteredException("Não existe usuário cadastrado com esse e-mail."));

        return new MyUserResponseDto(
                user.getId(),
                user.getName(),
                user.getAddress(),
                user.getEmail(),
                user.getPhone(),
                user.getUserType());
    }

    public List<MyUserResponseDto> getAllInactiveUsers() {
        List<MyUser> inactiveUserList = repository.findAllInactiveUsers()
                .orElseThrow(() -> new InactiveUserNotFoundException("Não há usuários aguardando aprovação."));
        List<MyUserResponseDto> resultList = new ArrayList<>();
        inactiveUserList.forEach(myUser -> resultList.add(new MyUserResponseDto(
                myUser.getId(),
                myUser.getName(),
                myUser.getAddress(),
                myUser.getEmail(),
                myUser.getPhone(),
                myUser.getUserType()
        )));
        return resultList;
    }

    public byte[] getUserDocumentPicture(String email) {
        System.out.println(email);
        MyUser user = repository.findByEmail(email).orElseThrow(
                () -> new UserNotRegisteredException("Não existe usuário cadastrado com esse e-mail."));
        return user.getDocumentPicture();
    }
}
