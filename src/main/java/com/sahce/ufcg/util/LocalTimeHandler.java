package com.sahce.ufcg.util;

import java.time.LocalDate;
import java.time.LocalTime;

public class LocalTimeHandler {
    public static boolean inputTimeIsInTimeRange(
            LocalTime inputStart, LocalTime inputEnd, LocalTime rangeStart, LocalTime rangeEnd
    ){
        return firstTimeIsEqualOrAfterSecondTime(inputStart, rangeStart) &&
                firstTimeIsEqualOrAfterSecondTime(inputEnd, rangeStart) &&
                firstTimeIsEqualOrBeforeSecondTime(inputStart, rangeEnd) &&
                firstTimeIsEqualOrBeforeSecondTime(inputEnd, rangeEnd);
    }

    private static boolean firstTimeIsEqualOrAfterSecondTime(LocalTime t1, LocalTime t2){
        System.out.println(t1 + " - " + t2 + " - " + (t1.isAfter(t2) || t1.equals(t2)));
        return t1.isAfter(t2) || t1.equals(t2);
    }

    private static boolean firstTimeIsEqualOrBeforeSecondTime(LocalTime t1, LocalTime t2){
        System.out.println(t1 + " - " + t2 + " - " + (t1.isBefore(t2) || t1.equals(t2)));
        return t1.isBefore(t2) || t1.equals(t2);
    }
}
