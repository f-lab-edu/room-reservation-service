package com.ryan.roomreservationservice.repository;

import com.ryan.roomreservationservice.domain.Room;
import com.ryan.roomreservationservice.persistance.RoomJpaRepository;
import com.ryan.roomreservationservice.repository.validator.RoomValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class RoomRepository {

    private final RoomJpaRepository roomJpaRepository;
    private final RoomValidator roomValidator;

    public List<Room> findAll() {
        return this.roomJpaRepository.findAll();
    }

    public Room findOneByRoomName(String roomName) {
        Optional<Room> room = this.roomJpaRepository.findByRoomName(roomName);
        this.roomValidator.assertRoomNotExist(room);

        return room.get();
    }
}
