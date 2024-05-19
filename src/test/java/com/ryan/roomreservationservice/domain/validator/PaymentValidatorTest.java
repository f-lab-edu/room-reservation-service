package com.ryan.roomreservationservice.domain.validator;

import com.ryan.roomreservationservice.domain.*;
import com.ryan.roomreservationservice.domain.enums.AccommodationStatus;
import com.ryan.roomreservationservice.utils.exception.ErrorMessage;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class PaymentValidatorTest {

    @Test
    public void 예약금액과_결제금액이_다를때_검증() {
        // given(준비): 어떠한 데이터가 준비되었을 때
        String name = "Ryan";
        List<Reservation> reservations = new ArrayList<>();
        List<PaymentHistory> paymentHistories = new ArrayList<>();
        Member member = new Member(name, reservations, paymentHistories);

        LocalDate start = LocalDate.parse("2024-02-01");
        LocalDate end = LocalDate.parse("2024-02-02");
        LocalDateRange localDateRange = new LocalDateRange(start, end);

        ZoneId zoneId = ZoneId.of("Asia/Seoul");
        String roomName = "그린룸";
        long price = 300000;
        Room room = new Room(zoneId, roomName, price);
        Accommodation accommodation = new Accommodation(room, AccommodationStatus.AVAILABLE);

        Reservation reservation = new Reservation(member, localDateRange, accommodation);
        long paymentAmount = 1000;

        // when(실행): 어떠한 함수를 실행하면
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> PaymentValidator.assertMismatchPaymentAmount(reservation, paymentAmount));

        // then(검증): 어떠한 결과가 나와야 한다.
        assertThat(exception.getMessage()).isEqualTo(ErrorMessage.PAYMENT_AMOUNT_NOT_MATCH_PRICE);

    }

}