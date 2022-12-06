package com.sahce.ufcg.dtos.scheduling;


import com.sahce.ufcg.models.TimesByDay;
import java.time.LocalDate;
import java.util.List;

/**
 * Esse DTO é criado a partir de um schedule (horário criado pelo admin do sistema) e representa
 * um scheduling (agendamento).
 * O sistema não armazena um entidade Scheduling, pois a entidade schedule é suficiente e isso diminui
 * a complexidade do gerenciamento dessas abstrações
 */
public class SchedulingResponseDto {
    private String placeName;
    private String initialDate;
    private String finalDate;
    private String ownerEmail;
    private boolean available;
    private List<TimesByDay> timesByDayList;
    public SchedulingResponseDto(
            String placeName, String initialDate, String finalDate,
            String ownerEmail, boolean available, List<TimesByDay> timesByDayList
    ){
        this.placeName = placeName;
        this.initialDate = initialDate;
        this.finalDate = finalDate;
        this.ownerEmail = ownerEmail;
        this.available = available;
        this.timesByDayList = timesByDayList;
    }

    public String getPlaceName() {
        return placeName;
    }

    public String getInitialDate() {
        return initialDate;
    }

    public String getFinalDate() {
        return finalDate;
    }

    public String getOwnerEmail() {
        return ownerEmail;
    }

    public boolean isAvailable() {
        return available;
    }

    public List<TimesByDay> getTimesByDayList() {
        return timesByDayList;
    }
}
