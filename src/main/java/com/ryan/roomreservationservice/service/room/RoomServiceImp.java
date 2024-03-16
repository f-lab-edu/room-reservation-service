package com.ryan.roomreservationservice.service.room;

import com.ryan.roomreservationservice.controller.room.RoomDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RoomServiceImp implements RoomService {

    private final RoomQuery roomQuery;

    @Override
    public List<RoomDto.GetRoomListResponse> getRoomList() {
        return roomQuery.findAll().stream()
                .map(room -> new RoomDto.GetRoomListResponse(room.getZoneCode(), room.getRoomName(), room.getRoomSize()))
                .collect(Collectors.toList());
    }
}
