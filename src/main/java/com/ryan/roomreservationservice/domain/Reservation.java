package com.ryan.roomreservationservice.domain;

import lombok.Getter;

@Getter
public class Reservation {
    private Member member;
    private LocalDateRange reservationDate;
    private Accommodation accommodation;

    public Reservation(Member member, LocalDateRange reservationDate, Accommodation accommodation) {
        this.member = member;
        this.reservationDate = reservationDate;
        this.accommodation = accommodation;
    }

}
