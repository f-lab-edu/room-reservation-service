package com.ryan.roomreservationservice.domain;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalTime;

@Builder
@Getter
public class TimeRange {
    private LocalTime startTime;
    private LocalTime endTime;
}
