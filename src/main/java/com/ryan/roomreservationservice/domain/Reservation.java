package com.ryan.roomreservationservice.domain;

import java.util.List;

public class Reservation {
    private Member member;
    private DateRange dateRange;
    private Accommodation accommodation;

    public boolean makeReservation(Member member, DateRange dateRange, Accommodation accommodation){
        this.member = member;
        this.dateRange = dateRange;
        this.accommodation = accommodation;

        return true;
    }

    public List<Reservation> getReservations(Member member){
        return null;
    }
}
