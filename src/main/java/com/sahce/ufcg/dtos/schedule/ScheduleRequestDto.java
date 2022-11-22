package com.sahce.ufcg.dtos.schedule;

import com.sun.istack.NotNull;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class ScheduleRequestDto {
    private LocalDate initialDate;
    private LocalDate finalDate;
    private LocalTime initialTime;
    private LocalTime finalTime;
    private String ownerEmail;
    private String placeName;

    private List<DayOfWeek> daysOfWeek;

    public ScheduleRequestDto(LocalDate initialDate, LocalDate finalDate, LocalTime initialTime,
                              LocalTime finalTime, List<DayOfWeek> daysOfWeek, String ownerEmail, String placeName) {
        this.initialDate = initialDate;
        this.finalDate = finalDate;
        this.initialTime = initialTime;
        this.finalTime = finalTime;
        this.daysOfWeek = daysOfWeek;
        this.ownerEmail = ownerEmail;
        this.placeName = placeName;
    }

    public ScheduleRequestDto() {
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

    public String getOwnerEmail() {
        return ownerEmail;
    }

    public void setOwnerEmail(String ownerEmail) {
        this.ownerEmail = ownerEmail;
    }

    public String getPlaceName() {
        return placeName;
    }

    public void setPlaceName(String placeName) {
        this.placeName = placeName;
    }

    public LocalTime getInitialTime() {
        return initialTime;
    }

    public void setInitialTime(LocalTime initialTime) {
        this.initialTime = initialTime;
    }

    public LocalTime getFinalTime() {
        return finalTime;
    }

    public void setFinalTime(LocalTime finalTime) {
        this.finalTime = finalTime;
    }

    public List<DayOfWeek> getDaysOfWeek() {
        return daysOfWeek;
    }

    public void setDaysOfWeek(List<DayOfWeek> daysOfWeek) {
        this.daysOfWeek = daysOfWeek;
    }
}
