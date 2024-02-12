package com.ryan.roomreservationservice.persistance;

import com.ryan.roomreservationservice.domain.Room;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoomJpaRepository extends JpaRepository<Room, Long> {
    Optional<Room> findByRoomName(String roomName);
}
