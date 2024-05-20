package com.ryan.roomreservationservice.domain;

import com.ryan.roomreservationservice.domain.enums.PaymentMethod;

public interface PaymentProcess {
    boolean support(PaymentMethod paymentMethod);
    PaymentInfo pay(Reservation reservation, long amount);
    void cancel(String transactionId, long refundAmount);
}
