package com.ryan.roomreservationservice.domain;

import lombok.Builder;
import lombok.Getter;

import java.time.Instant;

@Builder
@Getter
public class DateRange {
    private Instant startDate;
    private Instant endDate;
}
