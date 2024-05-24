package com.ryan.roomreservationservice.domain;

import com.ryan.roomreservationservice.domain.enums.PaymentMethod;
import com.ryan.roomreservationservice.domain.enums.PaymentStatus;
import com.ryan.roomreservationservice.utils.exception.ErrorMessage;

import java.math.BigDecimal;
import java.security.InvalidParameterException;
import java.time.LocalDate;
import java.util.List;

public class Payment {

    private final List<PaymentProcess> paymentProcesses;

    public Payment(List<PaymentProcess> paymentProcess) {
        this.paymentProcesses = paymentProcess;
    }

    public void requestPayment(Reservation reservation, PaymentMethod paymentMethod, BigDecimal amount) {
        assertMismatchPaymentAmount(reservation, amount);

        PaymentProcess paymentProcess = this.routingPaymentProcess(paymentMethod);
        PaymentInfo paymentInfo = paymentProcess.pay(reservation, amount);

        new PaymentHistory(
                PaymentStatus.PAY,
                reservation.getMember(),
                reservation,
                paymentMethod,
                paymentInfo.transactionId(),
                amount,
                paymentInfo.receipt()
        );

        reservation.completeReservation();
    }

    public void cancelPayment(PaymentHistory paymentHistory, LocalDate cancelDate) {
        PaymentMethod paymentMethod = paymentHistory.getPaymentMethod();
        String transactionId = paymentHistory.getTransactionId();
        Reservation reservation = paymentHistory.getReservation();

        //Todo 후추 결제 취소 내역 확인후 결제 취소 진행

        BigDecimal refundAmount = reservation.getReservationRefundAmount(cancelDate);
        PaymentProcess paymentProcess = this.routingPaymentProcess(paymentMethod);
        paymentProcess.cancel(transactionId, refundAmount);

        new PaymentHistory(
                PaymentStatus.CANCEL,
                paymentHistory.getMember(),
                reservation,
                paymentMethod,
                transactionId,
                refundAmount,
                paymentHistory.getReceipt()
        );
    }

    private PaymentProcess routingPaymentProcess(PaymentMethod paymentMethod) {
        return paymentProcesses.stream()
                .filter((paymentProcess -> paymentProcess.support(paymentMethod)))
                .findFirst()
                .orElseThrow(InvalidParameterException::new);
    }

    private static void assertMismatchPaymentAmount(Reservation reservation, BigDecimal paymentAmount) {
        BigDecimal reservationAmount = reservation.getReservationAmount();
        if (reservationAmount.compareTo(paymentAmount) != 0)
            throw new IllegalArgumentException(ErrorMessage.PAYMENT_AMOUNT_NOT_MATCH_PRICE);
    }
}
