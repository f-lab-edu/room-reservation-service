package com.ryan.roomreservationservice.service.client;

import com.ryan.roomreservationservice.controller.client.dto.request.GetRoomReservationListRequestDto;
import com.ryan.roomreservationservice.controller.client.dto.response.GetRoomReservationListResponseDto;
import com.ryan.roomreservationservice.repository.RoomReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RoomReservationClientService {

    private final RoomReservationRepository roomReservationRepository;

    public List<GetRoomReservationListResponseDto> getRoomReservationList(GetRoomReservationListRequestDto getRoomReservationListRequestDto) {
        Instant start = getRoomReservationListRequestDto.getStart();
        Instant end = getRoomReservationListRequestDto.getEnd();

        return this.roomReservationRepository.findAllByReservation(start, end)
                .stream()
                .map(roomReservation -> new GetRoomReservationListResponseDto(
                        roomReservation.getRoom().getZoneCode(),
                        roomReservation.getRoom().getRoomName(),
                        roomReservation.getRoom().getRoomSize(),
                        roomReservation.getReservationStatus().getStatus(),
                        roomReservation.getReservation().getStart(),
                        roomReservation.getReservation().getEnd()
                ))
                .collect(Collectors.toList());

    }

}
