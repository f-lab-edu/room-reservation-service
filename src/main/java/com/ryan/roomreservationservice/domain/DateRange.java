package com.ryan.roomreservationservice.domain;

import lombok.Value;

import java.time.Instant;

@Value
public class DateRange {
    Instant start;
    Instant end;
}
