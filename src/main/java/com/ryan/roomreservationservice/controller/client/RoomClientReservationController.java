package com.ryan.roomreservationservice.controller.client;

import com.ryan.roomreservationservice.controller.client.dto.request.GetRoomReservationListRequestDto;
import com.ryan.roomreservationservice.controller.client.dto.request.ReserveRequestDto;
import com.ryan.roomreservationservice.service.client.RoomReservationClientService;
import com.ryan.roomreservationservice.util.dto.SuccessResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/client/room-reservation/v1/room-reservation")
@RequiredArgsConstructor
public class RoomClientReservationController {

    private final RoomReservationClientService roomReservationClientService;

    @GetMapping("")
    public SuccessResponse getRoomReservationList(@Valid GetRoomReservationListRequestDto getRoomReservationListRequestDto) {
        return SuccessResponse.setSuccessResponse(this.roomReservationClientService.getRoomReservationList(getRoomReservationListRequestDto));
    }

    @PostMapping("")
    public SuccessResponse reserve(@RequestBody @Valid ReserveRequestDto reserveRequestDto) {
        return SuccessResponse.setSuccessResponse(this.roomReservationClientService.reserve(reserveRequestDto));
    }
}
