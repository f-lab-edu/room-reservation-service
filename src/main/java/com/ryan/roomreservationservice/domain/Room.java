package com.ryan.roomreservationservice.domain;

import java.util.List;

public class Room {

    public List<Room> getProvideRoomInfo() {
        return List.of();
    }

    public void reserve(Member member, DateRange dateRange, Accommodation accommodation) {

        //숙박 예약 가능 여부 확인
        accommodation.confirmReservation(accommodation);

        //결제 시도

        //예약 등록
        Reservation reservation = new Reservation();
        reservation.makeReservation(member, dateRange, accommodation);


    }
}
