package com.ryan.roomreservationservice.domain;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@Getter
public class Room {

    // 고유 키
    private Long roomId;

    // 상태(Ex: 노출 가능, 노출 불가능, 예약 불가능)
    private String roomStatus;

    // 객실 이름
    private String roomName;

    // 객실 크기
    private String roomSize;

    // 최소 인원
    private Integer minimumNumberOfPeople;

    // 최대 인원
    private Integer maximumNumberOfPeople;

    // 입실 시간
    private LocalDateTime checkInTime;

    // 퇴실 시간
    private LocalDateTime checkOutTime;

    // 객실 유형
    private List<String> roomType;

    // 객실 구조
    private List<String> roomStructure;

    // 가구
    private List<String> furniture;

    // 전자제품
    private List<String> electronics;

    // 편의도구
    private List<String> convenienceTools;

    // 객실테마
    private List<String> roomTheme;

    // 객실 설명
    private String roomDescription;

    //객실 예약 정보
    private List<RoomReservation> roomReservationList;

    public List<RoomReservation> addRoomReservation(RoomReservation roomReservation) {
        this.roomReservationList.add(roomReservation);
        return this.roomReservationList;
    }
}
