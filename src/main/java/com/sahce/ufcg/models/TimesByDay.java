package com.sahce.ufcg.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.sahce.ufcg.util.LocalTimeDeserializer;
import com.sahce.ufcg.util.LocalTimeSerializer;
import com.sun.istack.NotNull;

import javax.persistence.*;
import java.io.Serializable;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.Objects;

@Entity
public class TimesByDay implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @NotNull
    private DayOfWeek day;
    @NotNull
    @JsonSerialize(using = LocalTimeSerializer.class)
    @JsonDeserialize(using = LocalTimeDeserializer.class)
    private LocalTime initialTime;
    @NotNull
    @JsonSerialize(using = LocalTimeSerializer.class)
    @JsonDeserialize(using = LocalTimeDeserializer.class)
    private LocalTime finalTime;
    @NotNull
    @ManyToOne
    @JoinColumn(name = "schedule_id")
    @JsonBackReference
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
