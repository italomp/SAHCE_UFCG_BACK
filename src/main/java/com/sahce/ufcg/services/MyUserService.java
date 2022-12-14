package com.sahce.ufcg.services;

import com.sahce.ufcg.dtos.myUser.MyUserDtoRequest;
import com.sahce.ufcg.dtos.myUser.MyUserResponseDto;
import com.sahce.ufcg.exceptions.InactiveUserNotFoundException;
import com.sahce.ufcg.exceptions.UserNotRegisteredException;
import com.sahce.ufcg.models.MyUser;
import com.sahce.ufcg.repositories.MyUserRepository;
import com.sahce.ufcg.util.PasswordEncoder;
import com.sahce.ufcg.util.PictureUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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
        MyUser savedUser = this.repository.save(new MyUser(
                user.getName(),
                passwordEncoder.encode(user.getPassword()),
                user.getAddress(),
                user.getEmail(),
                user.getPhone(),
                user.getUserType()));

        return new MyUserResponseDto(
                savedUser.getId(),
                savedUser.getName(),
                savedUser.getAddress(),
                savedUser.getEmail(),
                savedUser.getPhone(),
                savedUser.getUserType());
    }

    public HttpStatus activeUser(String userEmail){
        MyUser user, savedUser;
        user = repository.findByEmail(userEmail).orElseThrow(
                () -> new UserNotRegisteredException("Não existe usuário cadastrado com esse e-mail."));
        user.setActive(true);
        repository.save(user);
        return HttpStatus.OK;
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

    public HttpStatus uploadUserDocumentPicture(MultipartFile documentPicture, String userEmail) throws IOException {
        MyUser user = repository.findByEmail(userEmail).orElseThrow(
                () -> new UserNotRegisteredException("Não existe usuário cadastrado com esse e-mail."));
        user.setDocumentPicture(PictureUtils.compressPicture(documentPicture.getBytes()));
        repository.save(user);
        return HttpStatus.OK;
    }

    public byte[] downloadDocumentPicture(String userEmail){
        MyUser user = repository.findByEmail(userEmail).orElseThrow(
                () -> new UserNotRegisteredException("Não existe usuário cadastrado com esse e-mail."));
        byte[] documentPicture = PictureUtils.decompressPicture(user.getDocumentPicture());
        return documentPicture;
    }
}
