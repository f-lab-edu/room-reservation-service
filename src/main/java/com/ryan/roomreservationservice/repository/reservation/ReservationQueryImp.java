package com.ryan.roomreservationservice.repository.reservation;

import com.ryan.roomreservationservice.domain.RoomReservation;
import com.ryan.roomreservationservice.persistence.RoomReservationJpaRepository;
import com.ryan.roomreservationservice.service.reservation.ReservationQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ReservationQueryImp implements ReservationQuery {

    private final RoomReservationJpaRepository roomReservationJpaRepository;

    @Override
    public List<RoomReservation> findAllByReservation(Instant start, Instant end) {
        return this.roomReservationJpaRepository.findAllByReservation(start, end);
    }
}
