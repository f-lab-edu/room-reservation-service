package com.ryan.roomreservationservice.domain;

import lombok.Getter;

@Getter
public class Reservation {
    private Member member;
    private DateRange reservationDate;
    private Accommodation accommodation;

    public Reservation(Member member, DateRange reservationDate, Accommodation accommodation) {
        this.member = member;
        this.reservationDate = reservationDate;
        this.accommodation = accommodation;
    }

}
