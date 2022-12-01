package com.sahce.ufcg.util.comparators;

import com.sahce.ufcg.models.TimesByDay;

import java.util.Comparator;

public class TimesByDayComparator implements Comparator<TimesByDay> {
    public TimesByDayComparator(){}

    @Override
    public int compare(TimesByDay t1, TimesByDay t2) {
        int biggerPriority = 2;
        int lowerPriority = 1;
        return (t1.getDay().compareTo(t2.getDay()) * biggerPriority) +
                (t1.getInitialTime().compareTo(t2.getInitialTime()) * lowerPriority);
    }
}
