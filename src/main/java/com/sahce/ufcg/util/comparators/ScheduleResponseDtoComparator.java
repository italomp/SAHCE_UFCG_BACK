package com.sahce.ufcg.util.comparators;

import com.sahce.ufcg.dtos.schedule.ScheduleResponseDto;

import java.util.Comparator;

public class ScheduleResponseDtoComparator implements Comparator<ScheduleResponseDto> {
    @Override
    public int compare(ScheduleResponseDto dto1, ScheduleResponseDto dto2) {
        return dto1.getInitialDate().compareTo(dto2.getInitialDate());
    }
}
