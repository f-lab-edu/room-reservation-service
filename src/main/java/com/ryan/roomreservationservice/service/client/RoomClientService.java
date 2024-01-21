package com.ryan.roomreservationservice.service.client;

import com.ryan.roomreservationservice.controller.client.dto.response.GetRoomListResponseDto;
import com.ryan.roomreservationservice.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RoomClientService {

    private final RoomRepository roomRepository;

    public List<GetRoomListResponseDto> getRoomList() {
        return this.roomRepository.findAll()
                .stream()
                .map(room -> new GetRoomListResponseDto(
                        room.getZoneCode(),
                        room.getRoomName(),
                        room.getRoomSize()
                ))
                .collect(Collectors.toList());
    }
}
