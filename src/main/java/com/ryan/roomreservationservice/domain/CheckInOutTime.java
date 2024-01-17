package com.ryan.roomreservationservice.domain;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalTime;

@Builder
@Getter
public class CheckInOutTime {
    // 입실일
    private LocalTime checkInTime;

    // 퇴실일
    private LocalTime checkOutTime;
}
