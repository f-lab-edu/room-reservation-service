package com.ryan.roomreservationservice.domain.enums;

import lombok.Getter;

@Getter
public enum AccommodationStatus {
    BLOCK("정지"),
    AVAILABLE("가능"),
    PENDING("대기"),
    CONFIRMED("완료");

    private String status;

    AccommodationStatus(String status) {
        this.status = status;
    }
}
