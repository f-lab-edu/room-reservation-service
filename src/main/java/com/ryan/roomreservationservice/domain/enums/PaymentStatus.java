package com.ryan.roomreservationservice.domain.enums;

public enum PaymentStatus {
    PAY("결제"),
    CANCEL("취소"),
    PARTIAL_CANCEL("부분취소");

    private final String status;

    PaymentStatus(String status) {
        this.status = status;
    }
}
