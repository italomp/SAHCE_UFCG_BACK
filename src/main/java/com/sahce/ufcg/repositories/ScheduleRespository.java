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
            "SELECT *" +
            "FROM schedule, schedule_days_of_week " +
            "WHERE id = schedule_id " +
            "AND initial_date = :pInitialDate " +
            "AND final_date = :pFinalDate " +
            "AND initial_time = :pInitialTime " +
            "AND final_time = :pFinalTime " +
            "AND place_id = :pPlaceId " +
            "AND days_of_week = :pDaysOfWeek",
            nativeQuery = true)
    Schedule checkIfThisScheduleExist(
            @Param("pInitialDate") LocalDate initialDate,
            @Param("pFinalDate") LocalDate finalDate,
            @Param("pInitialTime") LocalTime initialTime,
            @Param("pFinalTime") LocalTime finalTime,
            @Param("pPlaceId") Long placeId,
            @Param("pDaysOfWeek") int daysOfWeekList);

    /*
     * CHECA AS SEGUINTES CONDIÇÕES
     * ((mesmo dia e mesmo horário) AND (periodStartVelho <= periodStartNovo <= periodEndVelho))
     * OR
     * ((mesmo dia e mesmo horário) AND (periodStartVelho <= periodEndNovo <= periodEndVelho))
     * OR
	 * ((mesmo dia e mesmo horário) AND (periodStartNovo <= periodStartVelho <= periodEndNovo))
	 * OR
	 * ((mesmo dia e mesmo horário) AND (periodStartNovo <= periodEndVelho <= periodEndNovo))
	 *
	 * OBS: A query está simplificada (matematicamente/logicamente falando).
     * */
    @Query(value =
            "SELECT *" +
            "FROM schedule, schedule_days_of_week " +
            "WHERE (id = schedule_id " +
            "AND place_id = :pPlaceId) " +
            "AND " +
                /* mesmo dia e mesmo horário */
                "(days_of_week = :pDayOfWeek " +
                "AND initial_time = :pInitialTime " +
                "AND final_time = :pFinalTime) " +
            "AND (" +
                /* periodStartVelho <= periodStartNovo <= periodEndVelho */
                "(initial_date <= :pInitialDate " +
                "AND final_date >= :pInitialDate) " +
                "OR " +
                    /* periodStartVelho <= periodEndNovo <= periodEndVelho */
                    "(initial_date <= :pFinalDate " +
                    "AND final_date >= :pFinalDate) " +
                "OR " +
                    /* periodStartNovo <= periodStartVelho <= periodEndNovo */
                    "(initial_date >= :pInitialDate " +
                    "AND initial_date <= :pFinalDate) "  +
                "OR " +
                    /* periodStartNovo <= periodEndVelho <= periodEndNovo */
                    "(final_date >= :pInitialDate " +
                    "AND final_date <= :pFinalDate)" +
            ")",
    nativeQuery = true)
    Schedule checkConflict(
            @Param("pInitialDate") LocalDate initialDate,
            @Param("pFinalDate") LocalDate finalDate,
            @Param("pInitialTime") LocalTime initialTime,
            @Param("pFinalTime") LocalTime finalTime,
            @Param("pPlaceId") Long placeId,
            @Param("pDayOfWeek") int dayOfWeekList
    );

    @Query(value =
            "SELECT id, deprecated, disponible, initial_date, final_date, owner_id, place_id " +
            "FROM schedule s, place p " +
            "WHERE s.place_id = p.id AND P.name = :pPlaceName", nativeQuery = true)
    List<Schedule> findByPlaceName(@Param("pPlaceName") String placeName);
}
