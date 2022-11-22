package com.sahce.ufcg.models;

import com.sun.istack.NotNull;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.io.Serializable;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
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
    @NotNull
    @ElementCollection(targetClass= DayOfWeek.class)
    @Enumerated(EnumType.ORDINAL)
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private List<DayOfWeek> daysOfWeek;
    @NotNull
    private boolean deprecated;

    public Schedule(Place place, LocalDate initialDate, LocalDate finalDate, LocalTime initialTime,
                    LocalTime finalTime, List<DayOfWeek> daysOfWeek) {
        this.place = place;
        this.initialDate = initialDate;
        this.finalDate = finalDate;
        this.initialTime = initialTime;
        this.finalTime = finalTime;
        this.daysOfWeek = daysOfWeek;
        this.owner = null;
        this.disponibol = true;
        this.deprecated = false;
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

    public List<DayOfWeek> getDaysOfWeek() {
        return daysOfWeek;
    }

    public void setDaysOfWeek(List<DayOfWeek> daysOfWeek) {
        this.daysOfWeek = daysOfWeek;
    }

    public boolean isDeprecated() {
        return deprecated;
    }

    public void setDeprecated(boolean deprecated) {
        this.deprecated = deprecated;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Schedule schedule = (Schedule) o;
        return place.equals(schedule.place) && initialDate.equals(schedule.initialDate) &&
                finalDate.equals(schedule.finalDate) && initialTime.equals(schedule.initialTime) &&
                finalTime.equals(schedule.finalTime) && daysOfWeek.equals(schedule.daysOfWeek);
    }

    @Override
    public int hashCode() {
        return Objects.hash(place, initialDate, finalDate, initialTime, finalTime, daysOfWeek);
    }
}
