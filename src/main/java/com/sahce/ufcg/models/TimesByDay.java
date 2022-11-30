package com.sahce.ufcg.models;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.io.Serializable;
import java.time.DayOfWeek;
import java.time.LocalTime;

@Entity
public class TimesByDay implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @NotNull
    private DayOfWeek day;
    @NotNull
    private LocalTime initialTime;
    @NotNull
    private LocalTime finalTime;
    @NotNull
    @ManyToOne
    @JoinColumn(name = "schedule_id")
    private Schedule schedule;

    public TimesByDay(DayOfWeek day, LocalTime initialTime, LocalTime finalTime) {
        this.day = day;
        this.initialTime = initialTime;
        this.finalTime = finalTime;
    }

    public TimesByDay(DayOfWeek day) {
        this.day = day;
    }

    public TimesByDay(){}

    public DayOfWeek getDay() {
        return day;
    }

    public LocalTime getInitialTime() {
        return initialTime;
    }

    public LocalTime getFinalTime() {
        return finalTime;
    }

    public void setDay(DayOfWeek day) {
        this.day = day;
    }

    public void setInitialTime(LocalTime initialTime) {
        this.initialTime = initialTime;
    }

    public void setFinalTime(LocalTime finalTime) {
        this.finalTime = finalTime;
    }

    public Schedule getSchedule() {
        return schedule;
    }

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }
}
