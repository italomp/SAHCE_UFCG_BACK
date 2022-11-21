package com.sahce.ufcg.controllers;

import com.sahce.ufcg.dtos.place.PlaceResponseDto;
import com.sahce.ufcg.models.Place;
import com.sahce.ufcg.services.PlaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1")
public class PlaceController {
    @Autowired
    private PlaceService service;

    @PostMapping("/admin/places")
    public ResponseEntity<HttpStatus> save(@RequestBody Place place){
        return new ResponseEntity<>(service.save(place));
    }

    @GetMapping("/admin/places")
    public ResponseEntity<List<PlaceResponseDto>> getAll(){
        return new ResponseEntity<>(service.getAll(), HttpStatus.OK);
    }
}
