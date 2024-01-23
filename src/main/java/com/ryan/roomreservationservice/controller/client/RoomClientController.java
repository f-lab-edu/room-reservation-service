package com.ryan.roomreservationservice.controller.client;

import com.ryan.roomreservationservice.service.client.RoomClientService;
import com.ryan.roomreservationservice.util.dto.SuccessResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/client/room/v1/room")
@RequiredArgsConstructor
public class RoomClientController {

    private final RoomClientService roomClientService;

    @GetMapping("")
    public SuccessResponse getRoomList() {
        return SuccessResponse.setSuccessResponse(this.roomClientService.getRoomList());
    }

}
