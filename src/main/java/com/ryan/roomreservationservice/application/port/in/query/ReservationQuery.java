package com.ryan.roomreservationservice.application.port.in.query;

import com.ryan.roomreservationservice.domain.Accommodation;
import com.ryan.roomreservationservice.domain.record.LocalDateRange;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

public class ReservationQuery {

    @Getter
    @Builder
    @ToString
    public static class Main {
        private Long reservationId;
        private LocalDateRange reservationDate;
        private Accommodation accommodation;
    }
}
