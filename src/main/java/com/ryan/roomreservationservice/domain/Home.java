package com.ryan.roomreservationservice.domain;

import com.ryan.roomreservationservice.domain.record.LocalDateRange;

import java.util.List;

public class Home {
    public List<Room> getProvideRoomInfo() {
        return List.of();
    }

    public List<Accommodation> getAvailableRoomOnDate(LocalDateRange localDateRange) {
        return List.of();
    }

    public Reservation reserve(Member member, LocalDateRange localDateRange, Accommodation accommodation) {
        accommodation.confirmReservation(accommodation);

        Reservation reservation = new Reservation(member, localDateRange, accommodation);
        member.registerReservation(reservation);

        return reservation;
    }
}
