package com.sahce.ufcg.controllers;

import com.sahce.ufcg.dtos.myUser.MyUserResponseDto;
import com.sahce.ufcg.dtos.myUser.MyUserDtoRequest;
import com.sahce.ufcg.services.MyUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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

    @PostMapping(value = "/protected/users/documentPicture", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<HttpStatus> saveDocumentPicture(
            @RequestParam("documentPicture") MultipartFile documentPicture,
            @RequestParam("userEmail") String userEmail
    ) throws IOException {
        return new ResponseEntity<>(service.uploadUserDocumentPicture(documentPicture, userEmail));
    }

    @GetMapping("/admin/users/documentPicture")
    public ResponseEntity<?> getDocumentPicture(@RequestParam("userEmail") String userEmail){
        byte[] documentPicture = service.getUserDocumentPicture(userEmail);
        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.valueOf("image/png"))
                .body(documentPicture);
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
