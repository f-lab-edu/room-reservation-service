package com.ryan.roomreservationservice.domain;

import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
public class Room {

    // 고유 키
    private Long roomId;

    // 지역코드
    private String zoneId;

    // 상태(Ex: 노출 가능, 노출 불가능, 예약 불가능)
    private String roomStatus;

    // 객실 이름
    private String roomName;

    // 객실 크기
    private String roomSize;

    //객실 예약 정보
    @Builder.Default
    private List<RoomReservation> roomReservationList = new ArrayList<>();

    public void addRoomReservation(RoomReservation roomReservation) {
        this.roomReservationList.add(roomReservation);
    }

    public List<RoomReservation> getRoomReservationList() {
        return this.roomReservationList;
    }
}
