package com.ryan.roomreservationservice.domain;

import com.ryan.roomreservationservice.util.enums.ErrorType;
import com.ryan.roomreservationservice.util.enums.CommonStatusCode;
import com.ryan.roomreservationservice.util.exception.CommonException;
import com.ryan.roomreservationservice.util.exception.ErrorMessage;
import jakarta.persistence.Embeddable;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.Objects;

@Embeddable
@Getter
@NoArgsConstructor
public class DateRange {

    private Instant start;
    private Instant end;


    @Builder
    public DateRange(Instant start, Instant end) {
        if (Objects.isNull(start) || Objects.isNull(end)) {
            throw CommonException.builder()
                    .errorType(ErrorType.DEVELOPER)
                    .status(CommonStatusCode.FAIL.getStatusCode())
                    .clientErrorMessage(ErrorMessage.CANNOT_BE_NULL_VALUE)
                    .build();
        }

        if (start.isAfter(end)) {
            throw CommonException.builder()
                    .errorType(ErrorType.DEVELOPER)
                    .status(CommonStatusCode.FAIL.getStatusCode())
                    .clientErrorMessage(ErrorMessage.CANNOT_BE_EARLIER_THAN_THE_START_DATE)
                    .build();
        }

        this.start = start;
        this.end = end;
    }

    /**
     * @author Ryan
     * @description 특정 기간 확인하기
     * - isAfter: 파라미터 값 보다 Instant 값이 "이후" 라면 true
     * - isBefore: 파라미터 값 보다 Instant 값이 "이전" 라면 true
     */
    public boolean checkAvailabilityPeriod(DateRange dateRange) {
        if (this.start.equals(dateRange.start) || this.end.equals(dateRange.end))
            return false;

        if (this.start.isAfter(dateRange.start) && this.start.isBefore(dateRange.end))
            return false;

        if (this.end.isAfter(dateRange.start) && this.end.isBefore(dateRange.end))
            return false;

        return true;
    }
}
