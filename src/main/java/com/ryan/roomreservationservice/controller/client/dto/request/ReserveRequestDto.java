package com.ryan.roomreservationservice.controller.client.dto.request;

import lombok.Data;

import java.time.Instant;

@Data
public class ReserveRequestDto {
    String roomName;
    Instant reservationStartDate;
    Instant reservationEndDate;
}
