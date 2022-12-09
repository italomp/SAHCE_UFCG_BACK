package com.sahce.ufcg.services;

import com.sahce.ufcg.dtos.schedule.ScheduleRequestDto;
import com.sahce.ufcg.dtos.schedule.ScheduleResponseDto;
import com.sahce.ufcg.dtos.scheduling.SchedulingResponseDto;
import com.sahce.ufcg.exceptions.ConflictingEntityException;
import com.sahce.ufcg.exceptions.PlaceNotRegisteredException;
import com.sahce.ufcg.exceptions.ScheduleNotFoundException;
import com.sahce.ufcg.exceptions.UserNotRegisteredException;
import com.sahce.ufcg.models.MyUser;
import com.sahce.ufcg.models.Place;
import com.sahce.ufcg.models.Schedule;
import com.sahce.ufcg.models.TimesByDay;
import com.sahce.ufcg.repositories.MyUserRepository;
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
    @Autowired
    private MyUserRepository userRepository;

    public HttpStatus save(ScheduleRequestDto dto){
        // Verificar se o Place existe e usá-lo posteriormente
        Place place = placeRepository.findByName(dto.getPlaceName()).orElseThrow(
                () -> new PlaceNotRegisteredException("Não há espaços registrados com esse nome"));

        // Preparando o newSchedule
        Schedule newSchedule = new Schedule();
        newSchedule.setPlace(place);
        newSchedule.setInitialDate(dto.getInitialDate());
        newSchedule.setFinalDate(dto.getFinalDate());
        newSchedule.setReleaseInternalCommunity(dto.getReleaseInternalCommunity());
        newSchedule.setReleaseExternalCommunity(dto.getReleaseExternalCommunity());
        List<TimesByDay> timesByDayList = new ArrayList<>();
        dto.getTimesByDayMap().values().forEach(
                timesByDay -> {
                    timesByDay.setSchedule(newSchedule);
                    timesByDayList.add(timesByDay);
                }
        );
        newSchedule.setTimesByDayList(timesByDayList);

        // Verificar se os períodos e horários não são conflitantes
        isScheduleConflicts(newSchedule);

        // Salvando
        scheduleRepository.save(newSchedule);
        return HttpStatus.OK;
    }

    public void isScheduleConflicts(Schedule schedule){
        for(TimesByDay timesByDay: schedule.getTimesByDayList()){
            List<Schedule> savedScheduleList = scheduleRepository.findFromTheDateForwardByFinalDate(
                    schedule.getInitialDate(),
                    schedule.getFinalDate(),
                    timesByDay.getDay().ordinal(),
                    timesByDay.getInitialTime(),
                    timesByDay.getFinalTime(),
                    schedule.getPlace().getName());
            if (savedScheduleList.size() > 0) {
                throw new ConflictingEntityException("O novo horário é conflitante com algum horário existente.");
            }
        }
    }

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
                                schedule.getId(),
                                schedule.getPlace().getName(),
                                schedule.getInitialDate().toString(),
                                schedule.getFinalDate().toString(),
                                schedule.getOwner() != null ? schedule.getOwner().getEmail() : null, /* CORRIGIR ISSO NO SCHEUDLE */
                                schedule.isAvailable(),
                                schedule.getTimesByDayList()
                        ));
                    }
                });
        return schedulingList;
    }

    public HttpStatus createScheduling(long scheduleId, String userEmail) {
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(
                () ->new ScheduleNotFoundException("Schedule não encontrado"));
        MyUser user = userRepository.findByEmail(userEmail).orElseThrow(
                () -> new UserNotRegisteredException("Usuário não registrado"));

        schedule.setOwner(user);
        schedule.setAvailable(false);
        scheduleRepository.save(schedule);
        return HttpStatus.OK;
    }
}
