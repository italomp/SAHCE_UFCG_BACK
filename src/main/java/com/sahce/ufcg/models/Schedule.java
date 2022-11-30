package com.sahce.ufcg.models;

import com.sun.istack.NotNull;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.io.Serializable;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Map;
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
    @ManyToOne
    @JoinColumn(name = "owner_id")
    private MyUser ownerEmail;
    @NotNull
    private boolean disponibol;
    @NotNull
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "schedule", orphanRemoval = true)
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private Map<DayOfWeek, TimesByDay> timesByDayMap;
    @NotNull
    private boolean deprecated;

    public Schedule(Place place, LocalDate initialDate, LocalDate finalDate,
                    Map<DayOfWeek, TimesByDay> timesByDayMap) {
        this.place = place;
        this.initialDate = initialDate;
        this.finalDate = finalDate;
        this.timesByDayMap = timesByDayMap;
        this.ownerEmail = null;
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

    public MyUser getOwnerEmail() {
        return ownerEmail;
    }

    public void setOwnerEmail(MyUser ownerEmail) {
        this.ownerEmail = ownerEmail;
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

    public boolean isDeprecated() {
        return deprecated;
    }

    public void setDeprecated(boolean deprecated) {
        this.deprecated = deprecated;
    }

    public Map<DayOfWeek, TimesByDay> getTimesByDayMap() {
        return timesByDayMap;
    }

    public void setTimesByDayMap(Map<DayOfWeek, TimesByDay> timesByDayMap) {
        this.timesByDayMap = timesByDayMap;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Schedule schedule = (Schedule) o;
        return place.equals(schedule.place) && initialDate.equals(schedule.initialDate) &&
                finalDate.equals(schedule.finalDate) && timesByDayMap.equals(schedule.timesByDayMap);
    }

    @Override
    public int hashCode() {
        return Objects.hash(place, initialDate, finalDate, timesByDayMap);
    }
}
