package com.ryan.roomreservationservice.domain;

import lombok.Getter;

import java.util.List;

@Getter
public class Reservation {
    private Member member;
    private DateRange dateRange;
    private Accommodation accommodation;

    public Reservation(Member member, DateRange dateRange, Accommodation accommodation) {
        this.member = member;
        this.dateRange = dateRange;
        this.accommodation = accommodation;
    }

    public List<Reservation> getReservations(Member member){
        return null;
    }
}
