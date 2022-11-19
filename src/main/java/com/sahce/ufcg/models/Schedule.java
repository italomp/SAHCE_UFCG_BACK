package com.sahce.ufcg.models;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

/*
* Os horários repetem-se N dias por semana durante um período definido pelo usuário admin.
* Esse período pode ser, por exemplo: uma semana, um mês, um semestre, um ano, etc.
* */
@Entity
public class Schedule implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @NotNull
    @ManyToOne
    @JoinColumn(name = "place_id")
    private Place place;
    @NotNull
    private LocalDate initialDate;
    @NotNull
    private LocalDate finalDate;
    @NotNull
    private LocalTime initialTime;
    @NotNull
    private LocalTime finalTime;
    @ManyToOne
    @JoinColumn(name = "owner_id")
    private MyUser owner;
    @NotNull
    private boolean disponibol;

    // DIAS DA SEMANA - lista com os dias da semana


    public Schedule(Place place, LocalDate initialDate, LocalDate finalDate, LocalTime initialTime,
                    LocalTime finalTime) {
        this.place = place;
        this.initialDate = initialDate;
        this.finalDate = finalDate;
        this.initialTime = initialTime;
        this.finalTime = finalTime;
        this.owner = null;
        this.disponibol = true;
    }

    public Schedule() {
    }

    public long getId() {
        return id;
    }

    public Place getPlaceName() {
        return place;
    }

    public void setPlaceName(Place placeName) {
        this.place = place;
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

    public MyUser getOwner() {
        return owner;
    }

    public void setOwner(MyUser owner) {
        this.owner = owner;
    }

    public boolean isDisponibol() {
        return disponibol;
    }

    public void setDisponibol(boolean disponibol) {
        this.disponibol = disponibol;
    }

    public Place getPlace() {
        return place;
    }

    public void setPlace(Place place) {
        this.place = place;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Schedule schedule = (Schedule) o;
        return place.equals(schedule.place) && initialDate.equals(schedule.initialDate) &&
                finalDate.equals(schedule.finalDate) && initialTime.equals(schedule.initialTime) &&
                finalTime.equals(schedule.finalTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(place, initialDate, finalDate, initialTime, finalTime);
    }
}
