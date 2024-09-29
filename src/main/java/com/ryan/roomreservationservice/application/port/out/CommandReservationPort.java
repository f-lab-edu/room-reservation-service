package com.ryan.roomreservationservice.application.port.out;

import com.ryan.roomreservationservice.domain.Reservation;

public interface CommandReservationPort {
    void save(Reservation reservation);
}
