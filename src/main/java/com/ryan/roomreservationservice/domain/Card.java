package com.ryan.roomreservationservice.domain;

import com.ryan.roomreservationservice.domain.enums.Status;

import java.time.Instant;

public class Card {
    private Long cardId;
    private Member member;
    private Status status;
    private String pgType;
    private String paymentKey;
    private String cardName;
    private String cardNumber;
    private String ownerType;
    private Instant createdAt;

    public Card(Member member, Status status, String pgType, String paymentKey, String cardName, String cardNumber, String ownerType) {
        this.member = member;
        this.status = status;
        this.pgType = pgType;
        this.paymentKey = paymentKey;
        this.cardName = cardName;
        this.cardNumber = cardNumber;
        this.ownerType = ownerType;
        this.createdAt = Instant.now();
    }

    public void changeToInActiveStatus() {
        this.status = Status.INACTIVE;
    }
}
