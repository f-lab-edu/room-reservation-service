package com.ryan.roomreservationservice.domain;

import java.time.Instant;

public class DateRange {
    private Instant start;
    private Instant end;

    public DateRange(Instant start, Instant end) {
        this.start = start;
        this.end = end;
    }
}
