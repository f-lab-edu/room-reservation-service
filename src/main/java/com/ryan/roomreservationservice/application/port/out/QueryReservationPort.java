package com.ryan.roomreservationservice.application.port.out;

import com.ryan.roomreservationservice.domain.Accommodation;
import com.ryan.roomreservationservice.domain.Member;
import com.ryan.roomreservationservice.domain.Reservation;

import java.util.List;

public interface QueryReservationPort {
    List<Reservation> findByMember(Member member);
    Reservation findOneByMemberAndAccommodation(Member member, Accommodation accommodation);
}


