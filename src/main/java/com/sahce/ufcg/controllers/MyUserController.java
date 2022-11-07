package com.sahce.ufcg.controllers;

import com.sahce.ufcg.dtos.myUser.MyUserDtoResponse;
import com.sahce.ufcg.dtos.myUser.MyUserDtoRequest;
import com.sahce.ufcg.models.MyUser;
import com.sahce.ufcg.services.MyUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1")
public class MyUserController {
    @Autowired
    private MyUserService service;

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

//    @GetMapping("/protected/users/{email}")
//    public ResponseEntity<MyUserDtoResponse> getAuthoritiesByUser(@PathVariable String email){
//        return new ResponseEntity<>(service.getUserByEmail(email), HttpStatus.OK);
//    }
}
