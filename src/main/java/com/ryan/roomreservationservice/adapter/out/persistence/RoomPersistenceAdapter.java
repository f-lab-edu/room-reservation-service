package com.ryan.roomreservationservice.adapter.out.persistence;

import com.ryan.roomreservationservice.adapter.out.persistence.entity.RoomJpaRepository;
import com.ryan.roomreservationservice.adapter.out.persistence.mapper.RoomPersistenceMapper;
import com.ryan.roomreservationservice.application.port.out.QueryRoomPort;
import com.ryan.roomreservationservice.domain.Room;
import com.ryan.roomreservationservice.utils.exception.ErrorMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
class RoomPersistenceAdapter implements QueryRoomPort {
    private final RoomJpaRepository roomJpaRepository;
    private final RoomPersistenceMapper roomPersistenceMapper;

    @Override
    public Room findOneByName(String name) {
        return this.roomJpaRepository.findByName(name)
                .map(roomPersistenceMapper::mapToRoom)
                .orElseThrow(() -> new IllegalArgumentException(ErrorMessage.NOT_FOUNT_ROOM));
    }
}
