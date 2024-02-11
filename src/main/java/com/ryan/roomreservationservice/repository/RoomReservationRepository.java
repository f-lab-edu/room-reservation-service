package com.ryan.roomreservationservice.repository;

import com.ryan.roomreservationservice.domain.RoomReservation;
import com.ryan.roomreservationservice.persistance.RoomReservationJpaRepository;
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
}
