package com.ryan.roomreservationservice.repository;

import com.ryan.roomreservationservice.domain.DateRange;
import com.ryan.roomreservationservice.domain.Room;
import com.ryan.roomreservationservice.domain.RoomReservation;
import com.ryan.roomreservationservice.persistance.RoomReservationJpaRepository;
import com.ryan.roomreservationservice.util.enums.ReservationStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class RoomReservationRepository {

    private final RoomReservationJpaRepository roomReservationJpaRepository;

    public List<RoomReservation> findAllByReservation(Instant start, Instant end) {
        return this.roomReservationJpaRepository.findAllByReservation(start, end);
    }

    public void reserve(Room room, Instant reservationStartDate, Instant reservationEndDate) {
        RoomReservation roomReservation = RoomReservation.builder()
                .room(room)
                .reservationStatus(ReservationStatus.PENDING)
                .reservation(new DateRange(reservationStartDate, reservationEndDate))
                .build();

        this.roomReservationJpaRepository.save(roomReservation);
    }
}
