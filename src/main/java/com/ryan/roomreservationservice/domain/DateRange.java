package com.ryan.roomreservationservice.domain;

import com.ryan.roomreservationservice.utils.exception.ErrorMessage;

import java.time.Duration;
import java.time.Instant;
import java.util.Objects;

public record DateRange(Instant start, Instant end) {
    public DateRange {
        if (Objects.isNull(start) || Objects.isNull(end)) {
            throw new IllegalArgumentException(ErrorMessage.CANNOT_BE_NULL_VALUE);
        }

        if (start.isAfter(end)) {
            throw new IllegalArgumentException(ErrorMessage.CANNOT_BE_EARLIER_THAN_THE_START_DATE);
        }
    }

    public long calculateDayPeriod() {
        long hours = Duration.between(this.start, this.end).toHours();

        if (hours == 20) {
            return 1;
        }

        hours -= 20;
        return (hours / 24) + 1;
    }
}
