package com.ryan.roomreservationservice.controller.client;

import com.ryan.roomreservationservice.controller.client.dto.request.GetRoomReservationListRequestDto;
import com.ryan.roomreservationservice.service.client.RoomReservationClientService;
import com.ryan.roomreservationservice.util.dto.SuccessResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/client/room-reservation/v1/room-reservation")
@RequiredArgsConstructor
public class RoomClientReservationController {

    private final RoomReservationClientService roomReservationClientService;

    @GetMapping("")
    public SuccessResponse getRoomReservationList(@Valid GetRoomReservationListRequestDto getRoomReservationListRequestDto) {
        return SuccessResponse.setSuccessResponse(this.roomReservationClientService.getRoomReservationList(getRoomReservationListRequestDto));
    }
}
