package com.ryan.roomreservationservice.controller.client.dto.response;

import lombok.Data;

import java.time.Instant;

@Data
public class GetRoomReservationListResponseDto {
    String zoneCode;
    String roomName;
    String roomSize;
    String reservationStatus;
    Instant reservationStartDate;
    Instant reservationEndDate;


    public GetRoomReservationListResponseDto(String zoneCode, String roomName, String roomSize, String reservationStatus, Instant reservationStartDate, Instant reservationEndDate) {
        this.zoneCode = zoneCode;
        this.roomName = roomName;
        this.roomSize = roomSize;
        this.reservationStatus = reservationStatus;
        this.reservationStartDate = reservationStartDate;
        this.reservationEndDate = reservationEndDate;
    }
}
