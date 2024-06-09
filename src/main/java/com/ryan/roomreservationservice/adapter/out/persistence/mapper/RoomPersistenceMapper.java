package com.ryan.roomreservationservice.adapter.out.persistence.mapper;

import com.ryan.roomreservationservice.adapter.out.persistence.entity.RoomEntity;
import com.ryan.roomreservationservice.domain.Room;
import org.springframework.stereotype.Component;

import java.time.ZoneId;

@Component
public class RoomPersistenceMapper {

    public RoomEntity mapToRoomEntity(Room room) {
        return RoomEntity.builder()
                .roomId(room.getRoomId())
                .zoneId(room.getZoneId().getId())
                .name(room.getName())
                .basicPrice(room.getBasicPrice())
                .build();
    }

    public Room mapToRoom(RoomEntity roomEntity) {
        return Room.builder()
                .roomId(roomEntity.getRoomId())
                .zoneId(ZoneId.of(roomEntity.getZoneId()))
                .name(roomEntity.getName())
                .basicPrice(roomEntity.getBasicPrice())
                .build();
    }
}
