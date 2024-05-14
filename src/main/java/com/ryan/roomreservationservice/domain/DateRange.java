package com.ryan.roomreservationservice.domain;

import com.ryan.roomreservationservice.utils.exception.ErrorMessage;

import java.time.Duration;
import java.time.Instant;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

public record DateRange(Instant start, Instant end) {
    public DateRange(Instant start, Instant end) {
        if (Objects.isNull(start) || Objects.isNull(end)) {
            throw new IllegalArgumentException(ErrorMessage.CANNOT_BE_NULL_VALUE);
        }

        if (start.isAfter(end)) {
            throw new IllegalArgumentException(ErrorMessage.CANNOT_BE_EARLIER_THAN_THE_START_DATE);
        }

        this.start = start.truncatedTo(ChronoUnit.DAYS);
        this.end = end.truncatedTo(ChronoUnit.DAYS);
    }

    public long calculateDayPeriod(ZoneId zoneId) {
        return Duration.between(this.start.atZone(zoneId), this.end.atZone(zoneId)).toDays();
    }
}
