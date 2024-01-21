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

    Instant start;
    Instant end;

    public Period() {}

    public Period(Instant start, Instant end) {
        if (Objects.isNull(start) || Objects.isNull(end)) {
            throw CommonException.builder()
                    .errorType(ErrorType.DEVELOPER)
                    .status(StatusCode.FAIL.getStatusCode())
                    .clientErrorMessage(ErrorMessage.CANNOT_BE_NULL_VALUE)
                    .build();
        }

        if(start.isAfter(end)){
            throw CommonException.builder()
                    .errorType(ErrorType.DEVELOPER)
                    .status(StatusCode.FAIL.getStatusCode())
                    .clientErrorMessage(ErrorMessage.CANNOT_BE_EARLIER_THAN_THE_START_DATE)
                    .build();
        }

        this.start = start;
        this.end = end;
    }


}
