package com.sahce.ufcg.services;

import com.sahce.ufcg.dtos.schedule.ScheduleRequestDto;
import com.sahce.ufcg.dtos.schedule.ScheduleResponseDto;
import com.sahce.ufcg.dtos.scheduling.SchedulingResponseDto;
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

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static com.sahce.ufcg.util.LocalDateHandler.inputDateIsInDateRange;

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
                    String scheduleName = schedule.getPlace().getName().toLowerCase();
                    if(!placeName.toLowerCase().equals(scheduleName)) return;
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

    public List<SchedulingResponseDto> getSchedulingListByPlaceNameAndPeriodRange(
            String placeName, String strInitialDate, String strFinalDate
    ){
        List<Schedule> scheduleList = scheduleRepository.findAll();
        List<SchedulingResponseDto> schedulingList = new ArrayList<>();
        String placeNameParam = placeName.toLowerCase();
        LocalDate initialDate = LocalDate.parse(strInitialDate);
        LocalDate finalDate = LocalDate.parse(strFinalDate);
        scheduleList.forEach(schedule ->{
                    String schedulePlaceName = schedule.getPlace().getName().toLowerCase();
                    LocalDate scheduleInitialDate = schedule.getInitialDate();
                    LocalDate scheduleFinalDate = schedule.getFinalDate();
                    if(
                            placeNameParam.equals(schedulePlaceName) &&
                            inputDateIsInDateRange(scheduleInitialDate, scheduleFinalDate, initialDate, finalDate)
                    ){
                        schedulingList.add(new SchedulingResponseDto(
                                schedule.getPlace().getName(),
                                schedule.getInitialDate().toString(),
                                schedule.getFinalDate().toString(),
                                schedule.getOwnerEmail() != null ? schedule.getOwnerEmail().getEmail() : null, /* CORRIGIR ISSO NO SCHEUDLE */
                                schedule.isAvailable(),
                                schedule.getTimesByDayList()
                        ));
                    }
                });
        return schedulingList;
    }
}
