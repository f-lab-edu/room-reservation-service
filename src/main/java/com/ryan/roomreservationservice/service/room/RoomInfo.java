package com.ryan.roomreservationservice.service.room;

import lombok.Getter;
import lombok.ToString;

public class RoomInfo {

    @Getter
    @ToString
    public static class GetRoomList {
        public String zoneCode;
        public String roomName;
        public String roomSize;

        public GetRoomList(String zoneCode, String roomName, String roomSize) {
            this.zoneCode = zoneCode;
            this.roomName = roomName;
            this.roomSize = roomSize;
        }
    }
}
