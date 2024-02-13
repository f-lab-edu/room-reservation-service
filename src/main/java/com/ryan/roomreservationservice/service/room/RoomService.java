package com.ryan.roomreservationservice.service.room;

import com.ryan.roomreservationservice.controller.room.RoomDto;

import java.util.List;

public interface RoomService {

    List<RoomDto.GetRoomListResponse> getRoomList();
}
