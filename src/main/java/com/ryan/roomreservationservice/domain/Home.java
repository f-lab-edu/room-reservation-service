package com.ryan.roomreservationservice.domain;

import java.util.List;

public class Home {
    public List<Room> getProvideRoomInfo() {
        return List.of();
    }

    public List<Accommodation> getAvailableRoomOnDate(LocalDateRange localDateRange) {
        return List.of();
    }

    public Reservation reserve(Member member, LocalDateRange localDateRange, Accommodation accommodation) {
        //숙박 예약 가능 여부 확인
        accommodation.confirmReservation(accommodation);
        //예약 등록
        return new Reservation(member, localDateRange, accommodation);
    }
}
