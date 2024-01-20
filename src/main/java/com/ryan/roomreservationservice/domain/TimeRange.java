package com.ryan.roomreservationservice.domain;

import lombok.Value;

import java.time.LocalTime;

@Value
public class TimeRange {
    LocalTime start;
    LocalTime end;
}
