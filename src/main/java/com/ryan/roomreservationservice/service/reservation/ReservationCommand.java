package com.ryan.roomreservationservice.service.reservation;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.time.Instant;

/**
 * @author Ryan
 * @description Controller 요청 정보 변환을 담당하는 클래스
 */
public class ReservationCommand {

    @Getter
    @Builder
    @ToString
    public static class GetReservationListRequest {
        private Instant start;
        private Instant end;
    }

    @Getter
    @Builder
    @ToString
    public static class ReserveRequest  {
        private String roomName;
        private Instant reservationStartDate;
        private Instant reservationEndDate;
    }
}
