package com.sahce.ufcg.services;

import com.sahce.ufcg.dtos.schedule.ScheduleRequestDto;
import com.sahce.ufcg.exceptions.PersistenceException;
import com.sahce.ufcg.exceptions.PlaceNotRegisteredException;
import com.sahce.ufcg.exceptions.ScheduleAlreadyRegistered;
import com.sahce.ufcg.models.Place;
import com.sahce.ufcg.models.Schedule;
import com.sahce.ufcg.repositories.PlaceRepository;
import com.sahce.ufcg.repositories.ScheduleRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;


@Service
public class ScheduleService {
    @Autowired
    private ScheduleRespository scheduleRespository;
    @Autowired
    private PlaceRepository placeRepository;

    public HttpStatus save(ScheduleRequestDto dto){
        Place place = placeRepository.findByName(dto.getPlaceName()).orElseThrow(
                () -> new PlaceNotRegisteredException("Não há espaços registrados com esse nome"));
        Schedule newSchedule = new Schedule(
                place,
                dto.getInitialDate(),
                dto.getFinalDate(),
                dto.getInitialTime(),
                dto.getFinalTime(),
                dto.getDaysOfWeek());
        checkIfThisScheduleAlreadyExist(newSchedule);
        checkConflicts(newSchedule);
        scheduleRespository.save(newSchedule);
        return HttpStatus.OK;
    }

    public void checkIfThisScheduleAlreadyExist(Schedule schedule){
        schedule.getDaysOfWeek().stream().forEach(
                dayOfWeek -> {
                    Schedule possibleExistentSchedule = scheduleRespository.checkIfThisScheduleExist(
                            schedule.getInitialDate(),
                            schedule.getFinalDate(),
                            schedule.getInitialTime(),
                            schedule.getFinalTime(),
                            schedule.getPlace().getId(),
                            dayOfWeek.ordinal());
                    if(schedule.equals(possibleExistentSchedule)){
                        throw new ScheduleAlreadyRegistered("Esse horário já exite.");
                    }
                }
        );
    }

    public void checkConflicts(Schedule schedule){
        schedule.getDaysOfWeek().stream().forEach(
                dayOfWeek -> {
                    Schedule existentSchedule = scheduleRespository.checkConflict(
                            schedule.getInitialDate(),
                            schedule.getFinalDate(),
                            schedule.getInitialTime(),
                            schedule.getFinalTime(),
                            schedule.getPlace().getId(),
                            dayOfWeek.ordinal()
                    );
                    if(existentSchedule != null)
                        throw new ScheduleAlreadyRegistered("Conflito com horário existente.");
                }
        );
    }

}
