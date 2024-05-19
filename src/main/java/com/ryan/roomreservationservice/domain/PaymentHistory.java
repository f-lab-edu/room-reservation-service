package com.ryan.roomreservationservice.domain;

import com.ryan.roomreservationservice.domain.enums.PaymentStatus;

import java.time.Instant;

public class PaymentHistory {
    private Long paymentHistoryId;
    private PaymentStatus status;
    private Member member;
    private Accommodation accommodation;
    private String transactionId;
    private Long amount;
    private String receipt;
    private Instant createdAt;

    public PaymentHistory(PaymentStatus status, Member member, Accommodation accommodation, String transactionId, Long amount, String receipt) {
        this.status = status;
        this.member = member;
        this.accommodation = accommodation;
        this.transactionId = transactionId;
        this.amount = amount;
        this.receipt = receipt;
        this.createdAt = Instant.now();
    }
}
