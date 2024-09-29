package com.ryan.roomreservationservice.domain.record;

import com.ryan.roomreservationservice.utils.exception.ErrorMessage;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

public record LocalDateRange(LocalDate start, LocalDate end) {
    public LocalDateRange {
        if (Objects.isNull(start) || Objects.isNull(end)) {
            throw new IllegalArgumentException(ErrorMessage.CANNOT_BE_NULL_VALUE);
        }

        if (start.isAfter(end)) {
            throw new IllegalArgumentException(ErrorMessage.CANNOT_BE_EARLIER_THAN_THE_START_DATE);
        }
    }

    public long calculateDayPeriod() {
        return ChronoUnit.DAYS.between(this.start, this.end);
    }

    public long calculatePeriodBeforeStartDate(LocalDate beforeDate) {
        return ChronoUnit.DAYS.between(beforeDate, this.start);
    }

    public static LocalDateRange parse(String start, String end) {
        if (Objects.isNull(start) || Objects.isNull(end)) {
            throw new IllegalArgumentException(ErrorMessage.CANNOT_BE_NULL_VALUE);
        }

        return new LocalDateRange(LocalDate.parse(start), LocalDate.parse(end));
    }

    public void assertDateBeforeTheStart(LocalDate date) {
        if (this.start.isBefore(date))
            throw new IllegalArgumentException(ErrorMessage.CANCEL_REQUEST_DATE_MUST_BE_BEFORE_CHECK_IN);
    }

}
