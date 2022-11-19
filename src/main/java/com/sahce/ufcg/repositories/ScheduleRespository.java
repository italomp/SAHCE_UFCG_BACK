package com.sahce.ufcg.repositories;

import com.sahce.ufcg.models.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;

@Repository
public interface ScheduleRespository extends JpaRepository<Schedule, Long> {

    @Query(value =
            "SELECT *" +
            "FROM schedule s " +
            "WHERE s.initial_date = :pInitialDate " +
            "AND s.final_date = :pFinalDate " +
            "AND s.initial_time = :pInitialTime " +
            "AND s.final_time = :pFinalTime " +
            "AND s.place_id = :pPlaceId",
            nativeQuery = true)
    Schedule checkIfThisScheduleExist(
            @Param("pInitialDate") LocalDate initialDate,
            @Param("pFinalDate") LocalDate finalDate,
            @Param("pInitialTime") LocalTime inalTime,
            @Param("pFinalTime") LocalTime finalTime,
            @Param("pPlaceId") Long placeId);
}
