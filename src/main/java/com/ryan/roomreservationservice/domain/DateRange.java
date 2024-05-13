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

        if (this.extractHour(start) != 15 || this.extractHour(end) != 11) {
            throw new IllegalArgumentException(ErrorMessage.CHECK_AVAILABLE_TIME_FOR_RESERVATION);
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

    private long extractHour(Instant instant) {
        long epochSecond = instant.getEpochSecond();
        return (epochSecond % (60 * 60 * 24)) / (60 * 60);
    }
}
