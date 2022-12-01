package com.sahce.ufcg.dtos.schedule;

import com.sahce.ufcg.models.TimesByDay;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

public class ScheduleResponseDto implements Serializable {
    private transient LocalDate initialDate;
    private transient LocalDate finalDate;
    private String placeName;
    private String ownerEmail;
    private List<TimesByDay> timesByDayList;

    public ScheduleResponseDto(LocalDate initialDate, LocalDate finalDate, String placeName,
                               String ownerEmail, List<TimesByDay> timesByDayList) {
        this.initialDate = initialDate;
        this.finalDate = finalDate;
        this.placeName = placeName;
        this.ownerEmail = ownerEmail;
        this.timesByDayList = timesByDayList;
    }

    public LocalDate getInitialDate() {
        return initialDate;
    }

    public void setInitialDate(LocalDate initialDate) {
        this.initialDate = initialDate;
    }

    public LocalDate getFinalDate() {
        return finalDate;
    }

    public void setFinalDate(LocalDate finalDate) {
        this.finalDate = finalDate;
    }

    public String getPlaceName() {
        return placeName;
    }

    public void setPlaceName(String placeName) {
        this.placeName = placeName;
    }

    public String getOwnerEmail() {
        return ownerEmail;
    }

    public void setOwnerEmail(String ownerEmail) {
        this.ownerEmail = ownerEmail;
    }

    public List<TimesByDay> getTimesByDayList() {
        return timesByDayList;
    }

    public void setTimesByDayList(List<TimesByDay> timesByDayList) {
        this.timesByDayList = timesByDayList;
    }
}
