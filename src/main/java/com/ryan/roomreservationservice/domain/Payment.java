package com.ryan.roomreservationservice.domain;

import com.ryan.roomreservationservice.domain.enums.PaymentMethod;
import com.ryan.roomreservationservice.domain.enums.PaymentStatus;
import com.ryan.roomreservationservice.domain.validator.PaymentValidator;

import java.security.InvalidParameterException;
import java.util.List;

public class Payment {

    private final List<PaymentProcess> paymentProcesses;

    public Payment(List<PaymentProcess> paymentProcess) {
        this.paymentProcesses = paymentProcess;
    }

    public void requestPayment(Reservation reservation, PaymentMethod paymentMethod, long amount) {
        PaymentValidator.assertMismatchPaymentAmount(reservation, amount);

        PaymentProcess paymentProcess = this.routingPaymentProcess(paymentMethod);
        PaymentInfo paymentInfo = paymentProcess.pay(reservation, amount);

        new PaymentHistory(
                PaymentStatus.PAY,
                reservation.getMember(),
                reservation.getAccommodation(),
                paymentInfo.transactionId(),
                amount,
                paymentInfo.receipt()
        );
    }

    private PaymentProcess routingPaymentProcess(PaymentMethod paymentMethod) {
        return paymentProcesses.stream()
                .filter((paymentProcess ->  paymentProcess.support(paymentMethod)))
                .findFirst()
                .orElseThrow(InvalidParameterException::new);
    }
}
