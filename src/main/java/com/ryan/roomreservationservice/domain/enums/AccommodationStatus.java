package com.ryan.roomreservationservice.domain.enums;

public enum AccommodationStatus {
    BLOCK("정지"),
    AVAILABLE("가능"),
    PENDING("대기"),
    COMPLETED("완료");

    private final String status;

    AccommodationStatus(String status) {
        this.status = status;
    }
}
