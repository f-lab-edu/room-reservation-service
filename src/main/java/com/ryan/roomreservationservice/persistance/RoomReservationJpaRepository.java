package com.ryan.roomreservationservice.persistance;

import com.ryan.roomreservationservice.domain.RoomReservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomReservationJpaRepository extends JpaRepository<RoomReservation, Long> {
}
