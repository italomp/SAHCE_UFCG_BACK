package com.sahce.ufcg.controllers;

import com.sahce.ufcg.dtos.schedule.ScheduleRequestDto;
import com.sahce.ufcg.dtos.schedule.ScheduleResponseDto;
import com.sahce.ufcg.dtos.scheduling.SchedulingResponseDto;
import com.sahce.ufcg.services.ScheduleService;
import com.sahce.ufcg.util.SchedulingOperationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1")
public class ScheduleController {
    @Autowired
    private ScheduleService service;
    @PostMapping("/admin/schedules")
    public ResponseEntity<HttpStatus> save(@RequestBody ScheduleRequestDto dto){
        service.save(dto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/admin/schedules")
    public ResponseEntity<List<ScheduleResponseDto>> getAll(@RequestParam("placeName") String placeName){
        return new ResponseEntity<>(service.getAllByName(placeName), HttpStatus.OK);
    }

    // Dates would be in this format: yyyy-mm-dd
    @GetMapping("/protected/scheduling")
    public ResponseEntity<List<SchedulingResponseDto>> getSchedulingListByPlaceNameAndPeriodRange(
            @RequestParam("placeName") String placeName,
            @RequestParam("initialDate") String initialDate,
            @RequestParam("finalDate") String finalDate
    ){
        return new ResponseEntity<>(
                service.getSchedulingListByPlaceNameAndPeriodRange(placeName, initialDate, finalDate), HttpStatus.OK);
    }

    @PutMapping("/protected/scheduling")
    public ResponseEntity<HttpStatus> createScheduling(
            @RequestParam("scheduleId") long scheduleId, @RequestParam("userEmail") String userEmail,
            @RequestParam("schedulingOperation") SchedulingOperationUtils schedulingOperation
    ){
        return new ResponseEntity<>(service.createOrCancelScheduling(scheduleId, userEmail, schedulingOperation));
    }
}
