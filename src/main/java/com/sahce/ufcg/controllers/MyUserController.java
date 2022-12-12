package com.sahce.ufcg.controllers;

import com.sahce.ufcg.dtos.myUser.MyUserResponseDto;
import com.sahce.ufcg.dtos.myUser.MyUserDtoRequest;
import com.sahce.ufcg.services.MyUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

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
    public ResponseEntity<MyUserResponseDto> save(@RequestBody MyUserDtoRequest user){
        return new ResponseEntity<>(service.save(user), HttpStatus.OK);
    }

    @PutMapping("/admin/users")
    public ResponseEntity<MyUserResponseDto> activeUser(@RequestBody MyUserDtoRequest user){
        return new ResponseEntity<>(service.activeUser(user), HttpStatus.OK);
    }

    @GetMapping("/protected/users/{email}")
    public ResponseEntity<MyUserResponseDto> getAuthoritiesByUser(@PathVariable String email){
        return new ResponseEntity<>(service.getUserByEmail(email), HttpStatus.OK);
    }

    @GetMapping("/admin/users/inactive")
    public ResponseEntity<List<MyUserResponseDto>> getAllInactiveUsers(){
        return new ResponseEntity<>(service.getAllInactiveUsers(), HttpStatus.OK);
    }

    @GetMapping("/anonymous/users/document")
    public ResponseEntity<byte[]> getUserDocumentPicture(@RequestParam("userEmail") String email){
        return new ResponseEntity(service.getUserDocumentPicture(email), HttpStatus.OK);
    }
}
