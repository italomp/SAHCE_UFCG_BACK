package com.sahce.ufcg.controllers;

import com.sahce.ufcg.dtos.schedule.ScheduleRequestDto;
import com.sahce.ufcg.services.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1")
public class ScheduleController {
    @Autowired
    private ScheduleService service;
    // se preparar para busca case insensitve e para salvar case insensitive
    @PostMapping("/admin/schedules")
    public ResponseEntity<HttpStatus> save(@RequestBody ScheduleRequestDto dto){
        service.save(dto);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
