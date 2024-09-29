package com.ryan.roomreservationservice.domain.enums;

public enum PaymentMethod {
    REGULAR_CARD("일반카드"),
    REGISTER_CARD("등록카드");

    private final String paymentMethod;

    PaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }
}
