package com.ryan.roomreservationservice.adapter.in.web;

import com.ryan.roomreservationservice.adapter.in.web.dto.ReservationDto;
import com.ryan.roomreservationservice.adapter.in.web.mapper.ReservationDtoMapper;
import com.ryan.roomreservationservice.application.port.in.ReserveUseCase;
import com.ryan.roomreservationservice.utils.response.SuccessResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/reservation")
@Tag(name = "Reservation", description = "회원 예약하기 관련 API")
public class ReservationController {
    private final ReserveUseCase reserveUseCase;
    private final ReservationDtoMapper mapper;

    @PostMapping("")
    @Operation(summary = "객실 예약하기", description = "회원이 원하는 객실을 예약합니다.")
    public SuccessResponse<Void> reserve(
            @AuthenticationPrincipal User user,
            @RequestBody @Valid ReservationDto.ReserveRequestDto request
    ) {
        var command = this.mapper.mapToReserveCommand(user.getUsername(), request.getRoomName(), request.getReservationDate());
        this.reserveUseCase.reserve(command);
        return SuccessResponse.setDefaultResponse();
    }

    @GetMapping("")
    @Operation(summary = "회원 객실예약 내역 조회", description = "회원이 객실예약 내역을 조회합니다.")
    public SuccessResponse<List<ReservationDto.GetReservationsResponseDto>> getReservations(
            @AuthenticationPrincipal User user
    ) {
        var command = this.mapper.mapToGetReservationsByMemberCommand(user.getUsername());
        var result = this.reserveUseCase.getReservations(command);
        var data = this.mapper.mapToReservationList(result);
        return SuccessResponse.setSuccessResponse(data);
    }

    @GetMapping("/confirm")
    @Operation(summary = "객실 예약 확인하기", description = "회원이 현재 예약한 객실을 확인합니다.")
    public SuccessResponse<ReservationDto.ConfirmAccommodationReservationByMemberResponseDto> confirmAccommodationReservationByMember(
            @AuthenticationPrincipal User user,
            @Valid ReservationDto.ConfirmAccommodationReservationByMemberRequestDto request
    ) {
        var userId = user.getUsername();
        var command = this.mapper.mapToConfirmAccommodationReservationByMemberCommand(userId, request.getRoomName(), request.getStart(), request.getEnd());
        var result = this.reserveUseCase.confirmAccommodationReservationByMember(command);
        var data = this.mapper.mapToConfirmAccommodationReservationByMemberResponseDto(result);
        return SuccessResponse.setSuccessResponse(data);
    }

}