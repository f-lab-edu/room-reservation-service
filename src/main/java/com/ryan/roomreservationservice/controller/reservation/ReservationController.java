package com.ryan.roomreservationservice.controller.reservation;

import com.ryan.roomreservationservice.service.reservation.ReservationCommand;
import com.ryan.roomreservationservice.service.reservation.ReservationInfo;
import com.ryan.roomreservationservice.service.reservation.ReservationService;
import com.ryan.roomreservationservice.util.dto.SuccessResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/client/room-reservation/v1/room-reservation")
public class ReservationController {
    private final ReservationService reservationService;
    private final ReservationDtoMapper reservationDtoMapper;

    @GetMapping()
    public SuccessResponse getRoomReservationList(@Valid ReservationDto.GetReservationListRequest request) {
        ReservationCommand.GetReservationListRequest requestMapper = reservationDtoMapper.of(request);
        List<ReservationInfo.GetReservationList> reservationList = this.reservationService.getReservationList(requestMapper);
        List<ReservationDto.ReserveResponse> response = reservationDtoMapper.of(reservationList);
        return SuccessResponse.setSuccessResponse(response);
    }

    @PostMapping()
    public SuccessResponse reserve(@RequestBody @Valid ReservationDto.ReserveRequest request) {
        ReservationCommand.ReserveRequest requestMapper = reservationDtoMapper.of(request);
        boolean response = this.reservationService.reserve(requestMapper);
        return SuccessResponse.setSuccessResponse(response);
    }
}
