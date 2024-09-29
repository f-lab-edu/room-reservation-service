package com.ryan.roomreservationservice.adapter.in.web;

import com.ryan.roomreservationservice.adapter.in.web.dto.ReservationDto;
import com.ryan.roomreservationservice.adapter.in.web.mapper.ReservationDtoMapper;
import com.ryan.roomreservationservice.application.port.in.ReserveUseCase;
import com.ryan.roomreservationservice.utils.response.SuccessResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/reservation")
public class ReservationController {
    private final ReserveUseCase reserveUseCase;
    private final ReservationDtoMapper mapper;

    @PostMapping("")
    public SuccessResponse<Void> reserve(
            @AuthenticationPrincipal User user,
            @RequestBody @Valid ReservationDto.ReserveRequestDto request
    ) {
        var command = this.mapper.mapToReserveCommand(user.getUsername(), request.getRoomName(), request.getReservationDate());
        this.reserveUseCase.reserve(command);
        return SuccessResponse.setDefaultResponse();
    }

    @GetMapping("")
    public SuccessResponse<List<ReservationDto.GetReservationsResponseDto>> getReservations(
            @AuthenticationPrincipal User user
    ) {
        var command = this.mapper.mapToGetReservationsByMemberCommand(user.getUsername());
        var result = this.reserveUseCase.getReservations(command);
        var data = this.mapper.mapToReservationList(result);
        return SuccessResponse.setSuccessResponse(data);
    }

    @GetMapping("/confirm")
    public SuccessResponse<ReservationDto.ConfirmAccommodationReservationByMemberResponseDto> confirmAccommodationReservationByMember(
            @AuthenticationPrincipal User user,
            @Valid ReservationDto.ConfirmAccommodationReservationByMemberRequestDto request
    ) {
        var userId = user.getUsername();
        var command = this.mapper.mapToConfirmAccommodationReservationByMemberCommand(userId, request.getRoomName(), request.getReservationDate());
        var result = this.reserveUseCase.confirmAccommodationReservationByMember(command);
        var data = this.mapper.mapToConfirmAccommodationReservationByMemberResponseDto(result);
        return SuccessResponse.setSuccessResponse(data);
    }

}