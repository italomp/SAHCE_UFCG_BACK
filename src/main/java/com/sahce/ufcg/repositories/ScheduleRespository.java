package com.sahce.ufcg.repositories;

import com.sahce.ufcg.models.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Repository
public interface ScheduleRespository extends JpaRepository<Schedule, Long> {

    @Query(value =
            "SELECT s.id, s.deprecated, s.available, s.initial_date, s.final_date, s.owner_id, s.place_id " +
            "FROM schedule s, place p " +
            "WHERE s.place_id = p.id AND p.name = :pPlaceName", nativeQuery = true)
    List<Schedule> findByPlaceName(@Param("pPlaceName") String placeName);

    // Verificando sem schedule cadastrado no mesmo dia e horário dentro de um intervalo de tempo comum.
    @Query(value =
            "SELECT s.id, s.deprecated, s.available, s.initial_date, s.final_date, s.owner_id, s.place_id " +
            "FROM schedule s, times_by_day t, place p " +
            "WHERE s.id = t.schedule_id " +
            "AND s.place_id = p.id " +
            "AND ((:pInitialDate >= s.initial_date AND :pInitialDate <= s.final_date) " +
                   "OR (:pFinalDate >= s.initial_date AND :pFinalDate <= s.final_date)) " +
            "AND :pDay = t.day " +
            "AND ((:pInitialTime >= t.initial_time AND :pInitialTime <= t.final_time) " +
                    "OR (:pFinalTime >= t.initial_time AND :pFinalTime <= t.final_time)) " +
            "AND LOWER(:pPlaceName) = LOWER(p.name)", nativeQuery = true)
    List<Schedule> checkConflicts(
            @Param("pInitialDate") LocalDate initialDate,
            @Param("pFinalDate") LocalDate finalDate,
            @Param("pDay") int day,
            @Param("pInitialTime") LocalTime initialTime,
            @Param("pFinalTime") LocalTime finalTime,
            @Param("pPlaceName") String placeName);


    @Query(value =
            "SELECT s.id, s.deprecated, s.available, s.initial_date, s.final_date, s.owner_id, s.place_id " +
            "FROM schedule s, place p " +
            "WHERE s.place_id = p.id " +
            "AND p.id = :pPlaceId " +
            "AND s.owner_id = :pUserId " +
            "AND s.initial_date >= :pFisrtDayOfWeekAsDate " +
            "AND s.final_date <= :pLastDayOfWeekAsDate", nativeQuery = true)
    List<Schedule> findSchedulesByUserAndPeriodoAndPlace(
            @Param("pUserId") long userId,
            @Param("pPlaceId") long placeId,
            @Param("pFisrtDayOfWeekAsDate") LocalDate firstDayOfWeekAsDate,
            @Param("pLastDayOfWeekAsDate") LocalDate lastDayOfWeekAsDate);
}
