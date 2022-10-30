package com.sahce.ufcg.controllers;

import com.sahce.ufcg.dtos.myUser.MyUserDtoResponse;
import com.sahce.ufcg.dtos.myUser.MyUserDtoRequest;
import com.sahce.ufcg.services.MyUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class MyUserController {
    @Autowired
    private MyUserService service;

    public MyUserController() {
    }

    @PostMapping("/")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<MyUserDtoResponse> save(@RequestBody MyUserDtoRequest user){
        return new ResponseEntity<>(service.save(user), HttpStatus.OK);
    }

    @PutMapping("/")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<MyUserDtoResponse> activeUser(@RequestBody MyUserDtoRequest user){
        return new ResponseEntity<>(service.activeUser(user), HttpStatus.OK);
    }

    @GetMapping("/teste")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<HttpStatus> teste(){
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
