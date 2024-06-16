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
        String memberName;
        String roomName;
        LocalDateRange reservationDate;
    }

    @Getter
    @Builder
    @ToString
    public static class GetReservationsByMemberCommand {
        String memberName;
    }

    @Getter
    @Builder
    @ToString
    public static class ConfirmAccommodationReservationByMember {
        String memberName;
        String roomName;
        LocalDateRange reservationDate;
    }
}
