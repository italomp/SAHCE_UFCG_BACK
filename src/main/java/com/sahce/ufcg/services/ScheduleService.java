package com.sahce.ufcg.services;

import com.sahce.ufcg.dtos.schedule.ScheduleRequestDto;
import com.sahce.ufcg.dtos.schedule.ScheduleResponseDto;
import com.sahce.ufcg.exceptions.PlaceNotRegisteredException;
import com.sahce.ufcg.models.Place;
import com.sahce.ufcg.models.Schedule;
import com.sahce.ufcg.models.TimesByDay;
import com.sahce.ufcg.repositories.PlaceRepository;
import com.sahce.ufcg.repositories.ScheduleRespository;
import com.sahce.ufcg.util.comparators.ScheduleResponseDtoComparator;
import com.sahce.ufcg.util.comparators.TimesByDayComparator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ScheduleService {
    @Autowired
    private ScheduleRespository scheduleRepository;
    @Autowired
    private PlaceRepository placeRepository;

    public HttpStatus save(ScheduleRequestDto dto){
        Place place = placeRepository.findByName(dto.getPlaceName()).orElseThrow(
                () -> new PlaceNotRegisteredException("Não há espaços registrados com esse nome"));
        Schedule newSchedule = new Schedule();
        newSchedule.setPlace(place);
        newSchedule.setInitialDate(dto.getInitialDate());
        newSchedule.setFinalDate(dto.getFinalDate());
        List<TimesByDay> timesByDayList = new ArrayList<>();
        dto.getTimesByDayMap().values().forEach(
                timesByDay -> {
                    timesByDay.setSchedule(newSchedule);
                    timesByDayList.add(timesByDay);
                }
        );
        newSchedule.setTimesByDayList(timesByDayList);
        scheduleRepository.save(newSchedule);
        return HttpStatus.OK;
    }

//    public void checkIfThisScheduleAlreadyExist(Schedule schedule){
//        schedule.getDaysOfWeek().stream().forEach(
//                dayOfWeek -> {
//                    Schedule possibleExistentSchedule = scheduleRespository.checkIfThisScheduleExist(
//                            schedule.getInitialDate(),
//                            schedule.getFinalDate(),
//                            schedule.getInitialTime(),
//                            schedule.getFinalTime(),
//                            schedule.getNamePlace().getId(),
//                            dayOfWeek.ordinal());
//                    if(schedule.equals(possibleExistentSchedule)){
//                        throw new ScheduleAlreadyRegistered("Esse horário já exite.");
//                    }
//                }
//        );
//    }
//
//    public void checkConflicts(Schedule schedule){
//        schedule.getDaysOfWeek().stream().forEach(
//                dayOfWeek -> {
//                    Schedule existentSchedule = scheduleRespository.checkConflict(
//                            schedule.getInitialDate(),
//                            schedule.getFinalDate(),
//                            schedule.getInitialTime(),
//                            schedule.getFinalTime(),
//                            schedule.getNamePlace().getId(),
//                            dayOfWeek.ordinal()
//                    );
//                    if(existentSchedule != null)
//                        throw new ScheduleAlreadyRegistered("Conflito com horário existente.");
//                }
//        );
//    }

    public List<ScheduleResponseDto> getAllByName(String placeName){
        List<Schedule> scheduleList = scheduleRepository.findAll();
        List<ScheduleResponseDto> dtoList = new ArrayList<>();
        scheduleList.forEach(
                schedule -> {
                    List<TimesByDay> timesByDayList = schedule.getTimesByDayList();
                    timesByDayList.sort(new TimesByDayComparator());
                    dtoList.add(new ScheduleResponseDto(
                                    schedule.getInitialDate(),
                                    schedule.getFinalDate(),
                                    schedule.getPlace().getName(),
                                    null,
                                    timesByDayList
                            ));
                }
        );
        dtoList.sort(new ScheduleResponseDtoComparator());
        return dtoList;
    }
}
