package com.ryan.roomreservationservice.service.room;

import com.ryan.roomreservationservice.domain.Room;

import java.util.List;

public interface RoomQuery {
    List<Room> findAll();
    Room findOneByRoomName(String roomName);
}
