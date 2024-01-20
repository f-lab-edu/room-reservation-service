package com.ryan.roomreservationservice.controller.client;

import com.ryan.roomreservationservice.controller.client.dto.request.GetRoomRequestDto;
import com.ryan.roomreservationservice.util.dto.SuccessResponse;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/client/room/v1/room")
public class RoomClientController {

    @GetMapping("")
    public SuccessResponse getRoomList(@Valid GetRoomRequestDto getRoomRequestDto) {
        return SuccessResponse.setSuccessResponse(true);
    }


}
