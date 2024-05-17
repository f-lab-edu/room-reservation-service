package com.ryan.roomreservationservice.domain.validator;

import com.ryan.roomreservationservice.domain.Reservation;
import com.ryan.roomreservationservice.utils.exception.ErrorMessage;

public class PaymentValidator {
    public static void assertMismatchPaymentAmount(Reservation reservation, long paymentAmount) {
        long price = reservation.getAccommodation().getPaymentAmount(reservation.getReservationDate());
        if(price != paymentAmount) throw new IllegalArgumentException(ErrorMessage.PAYMENT_AMOUNT_NOT_MATCH_PRICE);
    }
}
