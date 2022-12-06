package com.sahce.ufcg.util;

import java.time.LocalDate;

public class LocalDateHandler {

    public static boolean inputDateIsInDateRange(
            LocalDate inputStart, LocalDate inputEnd, LocalDate rangeStart, LocalDate rangeEnd
    ){
        return firstDateIsEqualOrAfterSecondDate(inputStart, rangeStart) &&
                firstDateIsEqualOrAfterSecondDate(inputEnd, rangeStart) &&
                firstDateIsEqualOrBeforeSecondDate(inputStart, rangeEnd) &&
                firstDateIsEqualOrBeforeSecondDate(inputEnd, rangeEnd);
    }

    private static boolean firstDateIsEqualOrAfterSecondDate(LocalDate d1, LocalDate d2){
        return d1.isAfter(d2) || d1.isEqual(d2);
    }

    private static boolean firstDateIsEqualOrBeforeSecondDate(LocalDate d1, LocalDate d2){
        return d1.isBefore(d2) || d1.isEqual(d2);
    }
}
