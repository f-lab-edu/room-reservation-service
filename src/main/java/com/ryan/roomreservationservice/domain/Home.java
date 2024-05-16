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

        //결제 금액 조회
        accommodation.getPaymentAmount(localDateRange);

        //결제 시도

        //예약 등록
        return new Reservation(member, localDateRange, accommodation);
    }
}
