package com.ryan.roomreservationservice.domain;

import com.ryan.roomreservationservice.domain.enums.AccommodationStatus;
import com.ryan.roomreservationservice.domain.enums.PaymentMethod;
import com.ryan.roomreservationservice.domain.enums.PaymentStatus;
import com.ryan.roomreservationservice.domain.record.LocalDateRange;
import com.ryan.roomreservationservice.domain.interfaces.PaymentProcess;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
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

        Member member = Member.builder()
                .name("Ryan")
                .reservations(new ArrayList<>())
                .paymentHistories(new ArrayList<>())
                .cards(new ArrayList<>())
                .build();

        Room room = Room.builder()
                .roomId(1L)
                .zoneId(ZoneId.of("Asia/Seoul"))
                .name("그린룸")
                .basicPrice(BigDecimal.valueOf(300000))
                .build();

        Accommodation accommodation = Accommodation.builder()
                .accommodationId(1L)
                .room(room)
                .status(AccommodationStatus.AVAILABLE)
                .price(BigDecimal.valueOf(300000))
                .accommodationPeriod(LocalDateRange.parse("2024-06-07", "2024-06-08"))
                .build();

        LocalDate start = LocalDate.parse("2024-02-01");
        LocalDate end = LocalDate.parse("2024-02-02");
        LocalDateRange localDateRange = new LocalDateRange(start, end);

        Home home = new Home();
        Reservation reservation = home.reserve(member, localDateRange, accommodation);

        BigDecimal amount = BigDecimal.valueOf(300000);

        // when(실행): 어떠한 함수를 실행하면
        payment.requestPayment(reservation, PaymentMethod.REGULAR_CARD, amount);

        // then(검증): 어떠한 결과가 나와야 한다.
        assertThat(reservation.getAccommodation().getStatus()).isEqualTo(AccommodationStatus.COMPLETED);
    }

    @Test
    public void 결제_취소_검증() {
        // given(준비): 어떠한 데이터가 준비되었을 때
        PaymentProcess regularCardPayment = new RegularCardPayment();
        PaymentProcess registerCardPayment = new RegisterCardPayment();

        List<PaymentProcess> paymentProcesses = new ArrayList<>();
        paymentProcesses.add(regularCardPayment);
        paymentProcesses.add(registerCardPayment);

        Payment payment = new Payment(paymentProcesses);

        Member member = Member.builder()
                .name("Ryan")
                .reservations(new ArrayList<>())
                .paymentHistories(new ArrayList<>())
                .cards(new ArrayList<>())
                .build();

        Room room = Room.builder()
                .roomId(1L)
                .zoneId(ZoneId.of("Asia/Seoul"))
                .name("그린룸")
                .basicPrice(BigDecimal.valueOf(300000))
                .build();

        Accommodation accommodation = Accommodation.builder()
                .accommodationId(1L)
                .room(room)
                .status(AccommodationStatus.AVAILABLE)
                .price(BigDecimal.valueOf(300000))
                .accommodationPeriod(LocalDateRange.parse("2024-06-07", "2024-06-08"))
                .build();

        LocalDate start = LocalDate.parse("2024-02-01");
        LocalDate end = LocalDate.parse("2024-02-02");
        LocalDateRange localDateRange = new LocalDateRange(start, end);

        Home home = new Home();
        Reservation reservation = home.reserve(member, localDateRange, accommodation);

        String transactionId = "transactionId";
        String receipt = "receipt";
        BigDecimal amount = BigDecimal.valueOf(300000);

        PaymentHistory paymentHistory = new PaymentHistory(
                PaymentStatus.PAY,
                reservation.getMember(),
                reservation,
                PaymentMethod.REGULAR_CARD,
                transactionId,
                amount,
                receipt
        );

        LocalDate cancelDate = LocalDate.parse("2024-01-29");

        // when(실행): 어떠한 함수를 실행하면
        // then(검증): 어떠한 결과가 나와야 한다.
        payment.cancelPayment(paymentHistory, cancelDate);
    }

}