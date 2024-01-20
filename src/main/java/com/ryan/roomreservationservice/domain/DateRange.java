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

        if (Instant.now().isAfter(start) || Instant.now().isAfter(end)) {
            throw new IllegalArgumentException(ErrorMessage.CANNOT_BE_SET_TO_PAST_DATE);
        }

        this.start = start;
        this.end = end;
    }
}
