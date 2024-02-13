package com.ryan.roomreservationservice.controller.room;

import com.ryan.roomreservationservice.service.room.RoomService;
import com.ryan.roomreservationservice.util.dto.SuccessResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/client/room/v1/room")
@RequiredArgsConstructor
public class RoomController {

    private final RoomService roomService;
    private final RoomDtoMapper roomDtoMapper;

    @GetMapping("")
    public SuccessResponse getRoomList() {
        List<RoomDto.GetRoomListResponse> roomList = this.roomService.getRoomList();
        List<RoomDto.GetRoomListResponse> response = this.roomDtoMapper.of(roomList);
        return SuccessResponse.setSuccessResponse(response);
    }

}
