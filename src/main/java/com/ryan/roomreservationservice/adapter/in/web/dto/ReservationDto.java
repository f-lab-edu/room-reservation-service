package com.ryan.roomreservationservice.adapter.in.web.dto;

import com.ryan.roomreservationservice.domain.Accommodation;
import com.ryan.roomreservationservice.domain.record.LocalDateRange;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

public class ReservationDto {

    @Getter
    @Builder
    @ToString
    public static class ReserveRequestDto {
        String roomName;
        LocalDateRange reservationDate;
    }

    @Getter
    @Builder
    @ToString
    public static class GetReservationsResponseDto {
        private Long reservationId;
        private LocalDateRange reservationDate;
        private Accommodation accommodation;
    }

    @Getter
    @Builder
    @ToString
    public static class ConfirmAccommodationReservationByMemberRequestDto {
        String roomName;
        LocalDateRange reservationDate;
    }

    @Getter
    @Builder
    @ToString
    public static class ConfirmAccommodationReservationByMemberResponseDto {
        boolean confirmAccommodation;
    }
}
