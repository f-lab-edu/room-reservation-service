package com.ryan.roomreservationservice.application.service.mapper;

import com.ryan.roomreservationservice.application.port.in.query.ReservationQuery;
import com.ryan.roomreservationservice.domain.Reservation;
import org.springframework.stereotype.Component;

@Component
public class ReservationServiceMapper {

    public ReservationQuery.Main mapToMain(Reservation reservation) {
        return ReservationQuery.Main.builder()
                .reservationId(reservation.getReservationId())
                .reservationDate(reservation.getReservationDate())
                .accommodation(reservation.getAccommodation())
                .build();
    }
}
