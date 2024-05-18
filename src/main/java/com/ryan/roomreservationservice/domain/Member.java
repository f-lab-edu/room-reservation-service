package com.ryan.roomreservationservice.domain;

import lombok.Getter;

import java.util.List;

@Getter
public class Member {
    private String name;
    private List<Reservation> reservations;

    public Member(String name, List<Reservation> reservations) {
        this.name = name;
        this.reservations = reservations;
    }

    public void registerReservation(Reservation reservation) {
        this.reservations.add(reservation);
    }
}
