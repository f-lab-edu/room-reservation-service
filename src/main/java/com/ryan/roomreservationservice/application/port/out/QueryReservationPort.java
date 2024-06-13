package com.ryan.roomreservationservice.application.port.out;

import com.ryan.roomreservationservice.domain.Member;
import com.ryan.roomreservationservice.domain.Reservation;

import java.util.List;

public interface QueryReservationPort {
    List<Reservation> getReservationsByMember(Member member);
}


