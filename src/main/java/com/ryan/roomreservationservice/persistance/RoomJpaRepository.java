package com.ryan.roomreservationservice.persistance;

import com.ryan.roomreservationservice.domain.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomJpaRepository extends JpaRepository<Room, Long> {
}
