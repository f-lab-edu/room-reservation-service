package com.ryan.roomreservationservice.service.reservation;

import com.ryan.roomreservationservice.domain.RoomReservation;

import java.time.Instant;
import java.util.List;

public interface ReservationQuery {
    List<RoomReservation>  findAllByReservation(Instant start, Instant end);
}
