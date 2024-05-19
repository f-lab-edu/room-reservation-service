package com.ryan.roomreservationservice.domain;

import com.ryan.roomreservationservice.domain.enums.PaymentMethod;
import com.ryan.roomreservationservice.domain.enums.PaymentStatus;
import lombok.Getter;

import java.time.Instant;

@Getter
public class PaymentHistory {
    private Long paymentHistoryId;
    private PaymentStatus status;
    private Member member;
    private Reservation reservation;
    private PaymentMethod paymentMethod;
    private String transactionId;
    private Long amount;
    private String receipt;
    private Instant createdAt;

    public PaymentHistory(PaymentStatus status, Member member, Reservation reservation,  PaymentMethod paymentMethod, String transactionId, Long amount, String receipt) {
        this.status = status;
        this.member = member;
        this.reservation = reservation;
        this.paymentMethod = paymentMethod;
        this.transactionId = transactionId;
        this.amount = amount;
        this.receipt = receipt;
        this.createdAt = Instant.now();
    }
}
