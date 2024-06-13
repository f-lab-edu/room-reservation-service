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

    public Reservation reserve(Member member, LocalDateRange reservationDate, Accommodation accommodation) {
        accommodation.confirmReservation(accommodation);

        return Reservation.builder()
                .member(member)
                .accommodation(accommodation)
                .reservationDate(reservationDate)
                .build();
    }
}
