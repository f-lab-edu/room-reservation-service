package com.ryan.roomreservationservice.application.port.in;

import com.ryan.roomreservationservice.application.port.in.command.PaymentCommand;
import com.ryan.roomreservationservice.domain.enums.PaymentMethod;

public interface PaymentUseCase {
    PaymentMethod checkRegisteredPaymentMethods(PaymentCommand.RegisterPaymentMethodsCommand command);
    void requestPayment(PaymentCommand.RequestPaymentCommand command);
    void cancelPayment();
}
