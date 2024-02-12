package com.ryan.roomreservationservice.repository.room;

import com.ryan.roomreservationservice.domain.Room;
import com.ryan.roomreservationservice.persistance.RoomJpaRepository;
import com.ryan.roomreservationservice.repository.validator.RoomValidator;
import com.ryan.roomreservationservice.service.room.RoomQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class RoomQueryImp implements RoomQuery {
    private final RoomJpaRepository roomJpaRepository;
    private final RoomValidator roomValidator;

    @Override
    public List<Room> findAll() {
        return this.roomJpaRepository.findAll();
    }

    @Override
    public Room findOneByRoomName(String roomName) {
        Optional<Room> room = this.roomJpaRepository.findByRoomName(roomName);
        this.roomValidator.assertRoomNotExist(room);

        return room.get();
    }
}
