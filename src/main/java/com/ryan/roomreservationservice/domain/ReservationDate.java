package com.ryan.roomreservationservice.domain;

import lombok.Builder;
import lombok.Getter;

import java.time.Instant;

@Builder
@Getter
public class ReservationDate {
    // 예약일 시작일
    private Instant reservationStartDate;

    // 예약일 종료일
    private Instant reservationEndDate;
}
