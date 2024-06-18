package com.ryan.roomreservationservice.application.service;

import com.ryan.roomreservationservice.adapter.out.persistence.MemberPersistenceAdapter;
import com.ryan.roomreservationservice.application.port.in.PaymentUseCase;
import com.ryan.roomreservationservice.application.port.in.command.PaymentCommand;
import com.ryan.roomreservationservice.domain.enums.PaymentMethod;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentService implements PaymentUseCase {

    private final MemberPersistenceAdapter memberPersistenceAdapter;

    @Override
    public PaymentMethod checkRegisteredPaymentMethods(PaymentCommand.RegisterPaymentMethodsCommand command) {
        var memberName = command.getMemberName();
        var member = this.memberPersistenceAdapter.findOneByName(memberName);

        return null;
    }

    @Override
    public void requestPayment(PaymentCommand.RequestPaymentCommand command) {

    }

    @Override
    public void cancelPayment() {

    }
}
