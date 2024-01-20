package com.ryan.roomreservationservice.domain;

import com.ryan.roomreservationservice.util.exception.ErrorMessage;
import lombok.Value;

import java.time.Instant;
import java.util.Objects;

@Value
public class DateRange {
    Instant start;
    Instant end;

    public DateRange(Instant start, Instant end) {
        if (Objects.isNull(start) || Objects.isNull(end)) {
            throw new IllegalArgumentException(ErrorMessage.CANNOT_BE_NULL_VALUE);
        }

        if(start.isAfter(end)){
            throw new IllegalArgumentException(ErrorMessage.CANNOT_BE_EARLIER_THAN_THE_START_DATE);
        }

        this.start = start;
        this.end = end;
    }
}
