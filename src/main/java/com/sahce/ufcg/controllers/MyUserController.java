package com.sahce.ufcg.controllers;

import com.sahce.ufcg.dtos.myUser.MyUserDtoResponse;
import com.sahce.ufcg.dtos.myUser.MyUserDtoRequest;
import com.sahce.ufcg.dtos.myUser.MyUserDtoLogin;
import com.sahce.ufcg.services.CustomUserDetailsService;
import com.sahce.ufcg.services.MyUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1")
public class MyUserController {
    @Autowired
    private MyUserService service;
    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    public MyUserController() {
    }

    @PostMapping("/anonymous/users")
    public ResponseEntity<MyUserDtoResponse> save(@RequestBody MyUserDtoRequest user){
        return new ResponseEntity<>(service.save(user), HttpStatus.OK);
    }

    @PutMapping("/admin/users")
    public ResponseEntity<MyUserDtoResponse> activeUser(@RequestBody MyUserDtoRequest user){
        return new ResponseEntity<>(service.activeUser(user), HttpStatus.OK);
    }

    @GetMapping("/protected/users/teste")
    public ResponseEntity<HttpStatus> teste(){
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/anonymous/login")
    public ResponseEntity<MyUserDtoLogin> login(@RequestBody MyUserDtoLogin user){
        return (service.login(user.getEmail(), user.getPassword()) != null) ?
            new ResponseEntity<>(new MyUserDtoLogin(user.getEmail(), user.getPassword()), HttpStatus.OK) :
            new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
    }

//    @PostMapping(path = "/anonymous/login", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
//    public ResponseEntity<HttpStatus> login(@RequestParam MultiValueMap<String,String> paramMap){
//        String email = paramMap.getFirst("username");
//        String password = paramMap.getFirst("password");
//        return (service.login(email, password) != null) ?
//                new ResponseEntity<>(HttpStatus.OK) : new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
//    }
}
