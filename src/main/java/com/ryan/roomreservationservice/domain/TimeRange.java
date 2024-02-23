package com.ryan.roomreservationservice.domain;

import com.ryan.roomreservationservice.util.enums.ErrorType;
import com.ryan.roomreservationservice.util.enums.StatusCode;
import com.ryan.roomreservationservice.util.exception.CommonException;
import com.ryan.roomreservationservice.util.exception.ErrorMessage;
import jakarta.persistence.Embeddable;
import lombok.NoArgsConstructor;

import java.time.LocalTime;
import java.util.Objects;

@Embeddable
@NoArgsConstructor
public class TimeRange {
    private LocalTime start;
    private LocalTime end;

    private TimeRange(LocalTime start, LocalTime end) {
        this.start = start;
        this.end = end;
    }

    private static TimeRange create(LocalTime start, LocalTime end) {
        if (Objects.isNull(start) || Objects.isNull(end)) {
            throw CommonException.builder()
                    .errorType(ErrorType.DEVELOPER)
                    .status(StatusCode.FAIL.getStatusCode())
                    .clientErrorMessage(ErrorMessage.CANNOT_BE_NULL_VALUE)
                    .build();
        }

        if (start.isAfter(end)) {
            throw CommonException.builder()
                    .errorType(ErrorType.DEVELOPER)
                    .status(StatusCode.FAIL.getStatusCode())
                    .clientErrorMessage(ErrorMessage.CANNOT_BE_EARLIER_THAN_THE_START_DATE)
                    .build();
        }
        return new TimeRange(start, end);
    }

    public static TimeRange of(TimeRange timeRange, LocalTime end) {
        return create(timeRange.start, end);
    }

    public static TimeRange of(LocalTime start, TimeRange timeRange) {
        return create(start, timeRange.end);
    }

}