package com.ryan.roomreservationservice.application.port.in.command;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

public class PaymentCommand {

    @Getter
    @Builder
    @ToString
    public static class RegisterPaymentMethodsCommand {
        String memberName;
    }

    @Getter
    @Builder
    @ToString
    public static class RequestPaymentCommand {
        Long reservationId;
    }
}
