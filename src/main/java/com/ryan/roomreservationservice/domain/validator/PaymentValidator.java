package com.ryan.roomreservationservice.domain.validator;

import com.ryan.roomreservationservice.domain.Reservation;
import com.ryan.roomreservationservice.utils.exception.ErrorMessage;

import java.math.BigDecimal;

public class PaymentValidator {
    public static void assertMismatchPaymentAmount(Reservation reservation, BigDecimal paymentAmount) {
        BigDecimal reservationAmount = reservation.getReservationAmount();
        if (reservationAmount.compareTo(paymentAmount) != 0)
            throw new IllegalArgumentException(ErrorMessage.PAYMENT_AMOUNT_NOT_MATCH_PRICE);
    }
}
