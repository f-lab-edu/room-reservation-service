package com.ryan.roomreservationservice.application.port.in.command;

import com.ryan.roomreservationservice.domain.record.LocalDateRange;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

public class ReservationCommand {

    @Getter
    @Builder
    @ToString
    public static class ReserveCommand {
        String userId;
        String roomName;
        LocalDateRange reservationDate;
    }

    @Getter
    @Builder
    @ToString
    public static class GetReservationsByMemberCommand {
        String userId;
    }

    @Getter
    @Builder
    @ToString
    public static class ConfirmAccommodationReservationByMemberCommand {
        String userId;
        String roomName;
        LocalDateRange reservationDate;
    }
}
