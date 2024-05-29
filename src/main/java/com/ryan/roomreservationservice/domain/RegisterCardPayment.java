package com.ryan.roomreservationservice.domain;

import com.ryan.roomreservationservice.domain.enums.PaymentMethod;
import com.ryan.roomreservationservice.domain.record.PaymentInfo;
import com.ryan.roomreservationservice.domain.interfaces.PaymentProcess;

import java.math.BigDecimal;

public class RegisterCardPayment implements PaymentProcess {
    @Override
    public boolean support(PaymentMethod paymentMethod) {
        return PaymentMethod.REGISTER_CARD == paymentMethod;
    }

    @Override
    public PaymentInfo pay(Reservation reservation, BigDecimal price) {
        //Todo 외부 PG사 결제 로직 처리
        String transactionId = "";
        String receipt = "https://****.com";
        return new PaymentInfo(transactionId, receipt);
    }

    @Override
    public void cancel(String transactionId, BigDecimal refundAmount) {
        //Todo 외부 PG사 결제 취소 로직 처리
    }
}
