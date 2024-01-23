package com.ryan.roomreservationservice.controller.client.dto.response;

import lombok.Data;

@Data
public class GetRoomListResponseDto {
    public String zoneCode;
    public String roomName;
    public String roomSize;

    public GetRoomListResponseDto(String zoneCode, String roomName, String roomSize) {
        this.zoneCode = zoneCode;
        this.roomName = roomName;
        this.roomSize = roomSize;
    }
}
