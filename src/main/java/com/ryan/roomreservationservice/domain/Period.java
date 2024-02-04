package com.ryan.roomreservationservice.domain;

import com.ryan.roomreservationservice.util.enums.ErrorType;
import com.ryan.roomreservationservice.util.enums.StatusCode;
import com.ryan.roomreservationservice.util.exception.CommonException;
import com.ryan.roomreservationservice.util.exception.ErrorMessage;
import jakarta.persistence.Embeddable;
import lombok.Getter;

import java.time.Instant;
import java.util.Objects;

@Embeddable
@Getter
public class Period {

    private Instant start;
    private Instant end;

    public Period() {
    }

    public Period(Instant start, Instant end) {
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

        this.start = start;
        this.end = end;
    }

    /**
     * @author Ryan
     * @description 사용 가능 기간 확인하기
     * - isAfter: 현재 Instant(A)가 매개변수로 전달된 Instant(B)보다 "이후" 인지 확인(EX: A.isAfter(B))
     * - isBefore: 현재 Instant(A)가 매개변수로 전달 된 Instant(B)보다 "이전"인지 확인 (EX: A.isBefore(B))
     */
    public boolean checkAvailabilityPeriod(Instant start, Instant end) {
        if (Instant.now().isAfter(start)) return false;

        if (this.start.equals(start) && this.end.equals(end))
            return false;

        if (this.start.isAfter(start) && this.start.isBefore(end))
            return false;

        if (this.end.isAfter(start) && this.end.isBefore(end))
            return false;

        return true;
    }
}
