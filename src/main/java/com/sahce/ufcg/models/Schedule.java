package com.sahce.ufcg.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.sun.istack.NotNull;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

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
    private LocalDate releaseInternalCommunity;
    @NotNull
    private LocalDate releaseExternalCommunity;
    @ManyToOne
    @JoinColumn(name = "owner_id")
    private MyUser owner;
    @NotNull
    private boolean available;
    @NotNull
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "schedule", orphanRemoval = true)
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    @JsonManagedReference
    private List<TimesByDay> timesByDayList;
    @NotNull
    private boolean deprecated;

    public Schedule() {
        this.available = true;
        this.deprecated = false;
    }

    public long getId() {
        return id;
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

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean disponible) {
        this.available = disponible;
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

    public List<TimesByDay> getTimesByDayList() {
        return timesByDayList;
    }

    public void setTimesByDayList(List<TimesByDay> timesByDayList) {
        this.timesByDayList = timesByDayList;
    }

    public LocalDate getReleaseInternalCommunity() {
        return releaseInternalCommunity;
    }

    public void setReleaseInternalCommunity(LocalDate releaseInternalCommunity) {
        this.releaseInternalCommunity = releaseInternalCommunity;
    }

    public LocalDate getReleaseExternalCommunity() {
        return releaseExternalCommunity;
    }

    public void setReleaseExternalCommunity(LocalDate releaseExternalCommunity) {
        this.releaseExternalCommunity = releaseExternalCommunity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Schedule schedule = (Schedule) o;
        return place.equals(schedule.place) && initialDate.equals(schedule.initialDate) &&
                finalDate.equals(schedule.finalDate) && timesByDayList.equals(schedule.timesByDayList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(place, initialDate, finalDate, timesByDayList);
    }
}
