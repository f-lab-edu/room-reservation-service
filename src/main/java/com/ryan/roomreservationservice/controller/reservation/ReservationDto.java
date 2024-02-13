package com.ryan.roomreservationservice.controller.reservation;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.Instant;

public class ReservationDto {

    @Getter
    @Setter
    @ToString
    public static class GetReservationListRequest {
        @NotNull(message = "예약 시작일을 설정해주세요.")
        @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ")
        private Instant start;

        @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ")
        private Instant end;
    }

    @Getter
    @Setter
    @ToString
    public static class ReserveRequest {
        private String roomName;
        private Instant reservationStartDate;
        private Instant reservationEndDate;
    }

    @Getter
    @Builder
    @ToString
    public static class ReserveResponse {
        private String zoneCode;
        private String roomName;
        private String roomSize;
        private String reservationStatus;
        private Instant reservationStartDate;
        private Instant reservationEndDate;
    }
}
