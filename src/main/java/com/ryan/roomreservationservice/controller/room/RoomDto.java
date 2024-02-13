package com.ryan.roomreservationservice.controller.room;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

public class RoomDto {

    @Getter
    @Builder
    @ToString
    public static class GetRoomListResponse {
        public String zoneCode;
        public String roomName;
        public String roomSize;

        public GetRoomListResponse(String zoneCode, String roomName, String roomSize) {
            this.zoneCode = zoneCode;
            this.roomName = roomName;
            this.roomSize = roomSize;
        }
    }
}
