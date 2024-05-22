package com.ryan.roomreservationservice.domain;

import com.ryan.roomreservationservice.domain.enums.PaymentMethod;

import java.math.BigDecimal;

public interface PaymentProcess {
    boolean support(PaymentMethod paymentMethod);
    PaymentInfo pay(Reservation reservation, BigDecimal amount);
    void cancel(String transactionId, BigDecimal refundAmount);
}
