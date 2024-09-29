package com.ryan.roomreservationservice.adapter.in.web.mapper;

import com.ryan.roomreservationservice.adapter.in.web.dto.ReservationDto;
import com.ryan.roomreservationservice.application.port.in.command.ReservationCommand;
import com.ryan.roomreservationservice.application.port.in.query.ReservationQuery;
import com.ryan.roomreservationservice.domain.record.LocalDateRange;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ReservationDtoMapper {

    public ReservationCommand.ReserveCommand mapToReserveCommand(
            String userId,
            String roomName,
            LocalDateRange reservationDate
    ) {
        return ReservationCommand.ReserveCommand.builder()
                .userId(userId)
                .roomName(roomName)
                .reservationDate(reservationDate)
                .build();
    }

    public ReservationCommand.GetReservationsByMemberCommand mapToGetReservationsByMemberCommand(
            String userId
    ) {
        return ReservationCommand.GetReservationsByMemberCommand.builder()
                .userId(userId)
                .build();
    }

    public List<ReservationDto.GetReservationsResponseDto> mapToReservationList(List<ReservationQuery.Main> info) {
        return info.stream()
                .map(main ->
                        ReservationDto.GetReservationsResponseDto.builder()
                                .reservationId(main.getReservationId())
                                .reservationDate(main.getReservationDate())
                                .accommodation(main.getAccommodation())
                                .build()
                )
                .collect(Collectors.toList());


    }

    public ReservationCommand.ConfirmAccommodationReservationByMemberCommand mapToConfirmAccommodationReservationByMemberCommand(
            String userId,
            String roomName,
            LocalDateRange reservationDate
    ) {
        return ReservationCommand.ConfirmAccommodationReservationByMemberCommand.builder()
                .userId(userId)
                .roomName(roomName)
                .reservationDate(reservationDate)
                .build();
    }

    public ReservationDto.ConfirmAccommodationReservationByMemberResponseDto mapToConfirmAccommodationReservationByMemberResponseDto(
            boolean confirmAccommodation
    ) {
        return ReservationDto.ConfirmAccommodationReservationByMemberResponseDto.builder()
                .confirmAccommodation(confirmAccommodation)
                .build();
    }
}
