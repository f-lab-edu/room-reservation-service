package com.ryan.roomreservationservice.domain;

import com.ryan.roomreservationservice.domain.enums.AccommodationStatus;
import com.ryan.roomreservationservice.domain.enums.PaymentMethod;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class PaymentTest {

    @Test
    public void 올바른_결제금액을_통해_검증() {
        // given(준비): 어떠한 데이터가 준비되었을 때
        PaymentProcess regularCardPayment = new RegularCardPayment();
        PaymentProcess registerCardPayment = new RegisterCardPayment();

        List<PaymentProcess> paymentProcesses = new ArrayList<>();
        paymentProcesses.add(regularCardPayment);
        paymentProcesses.add(registerCardPayment);

        Payment payment = new Payment(paymentProcesses);

        String name = "Ryan";
        List<Reservation> reservations = new ArrayList<>();
        Member member = new Member(name, reservations);

        ZoneId zoneId = ZoneId.of("Asia/Seoul");
        String roomName = "그린룸";
        long price = 300000;

        Room room = new Room(zoneId, roomName, price);

        Accommodation accommodation = new Accommodation(room, AccommodationStatus.AVAILABLE);

        LocalDate start = LocalDate.parse("2024-02-01");
        LocalDate end = LocalDate.parse("2024-02-02");
        LocalDateRange localDateRange = new LocalDateRange(start, end);

        Home home = new Home();
        Reservation reservation = home.reserve(member, localDateRange, accommodation);

        long amount = 300000;

        // when(실행): 어떠한 함수를 실행하면
        payment.requestPayment(reservation, PaymentMethod.REGULAR_CARD, amount);

        // then(검증): 어떠한 결과가 나와야 한다.
        assertThat(reservation.getAccommodation().getStatus()).isEqualTo(AccommodationStatus.COMPLETED);
    }

}