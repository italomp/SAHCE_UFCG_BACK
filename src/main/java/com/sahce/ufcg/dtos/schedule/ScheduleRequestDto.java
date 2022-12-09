package com.sahce.ufcg.dtos.schedule;


import com.sahce.ufcg.models.TimesByDay;

import java.io.Serializable;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Map;

public class ScheduleRequestDto implements Serializable {
    private transient LocalDate initialDate;
    private transient LocalDate finalDate;
    private String placeName;
    private String ownerEmail;
    private  Map<DayOfWeek, TimesByDay> timesByDayMap;
    private LocalDate releaseInternalCommunity;
    private LocalDate releaseExternalCommunity;

    public ScheduleRequestDto() {
    }

    public LocalDate getReleaseInternalCommunity() {
        return releaseInternalCommunity;
    }

    public LocalDate getReleaseExternalCommunity() {
        return releaseExternalCommunity;
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

    public String getOwnerEmail() {
        return ownerEmail;
    }

    public String getPlaceName() {
        return placeName;
    }

    public Map<DayOfWeek, TimesByDay> getTimesByDayMap() {
        return timesByDayMap;
    }

}
