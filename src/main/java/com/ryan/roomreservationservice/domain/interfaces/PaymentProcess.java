package com.ryan.roomreservationservice.domain.interfaces;

import com.ryan.roomreservationservice.domain.Reservation;
import com.ryan.roomreservationservice.domain.enums.PaymentMethod;
import com.ryan.roomreservationservice.domain.record.PaymentInfo;

import java.math.BigDecimal;

public interface PaymentProcess {
    boolean support(PaymentMethod paymentMethod);
    PaymentInfo pay(Reservation reservation, BigDecimal amount);
    void cancel(String transactionId, BigDecimal refundAmount);
}
