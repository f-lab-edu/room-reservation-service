package com.ryan.roomreservationservice.repository;

import com.ryan.roomreservationservice.persistence.ReservationJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ReservationRepository {
    private final ReservationJpaRepository reservationJpaRepository;
}
