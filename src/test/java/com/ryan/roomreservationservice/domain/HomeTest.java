package com.ryan.roomreservationservice.domain;

import com.ryan.roomreservationservice.domain.enums.AccommodationStatus;
import com.ryan.roomreservationservice.domain.record.LocalDateRange;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class HomeTest {

    @Test
    public void 예약하기() {
        // given(준비): 어떠한 데이터가 준비되었을 때
        String name = "Ryan";
        List<Reservation> reservations = new ArrayList<>();
        List<PaymentHistory> paymentHistories = new ArrayList<>();
        List<Card> cards = new ArrayList<>();
        Member member = new Member(name, reservations, paymentHistories, cards);

        Room room = Room.builder()
                .roomId(1L)
                .zoneId(ZoneId.of("Asia/Seoul"))
                .name("그린룸")
                .basicPrice(BigDecimal.valueOf(300000))
                .build();

        LocalDate start = LocalDate.parse("2024-02-01");
        LocalDate end = LocalDate.parse("2024-02-03");

        LocalDateRange reservationDate = new LocalDateRange(start, end);
        Accommodation accommodation = Accommodation.builder()
                .accommodationId(1L)
                .room(room)
                .status(AccommodationStatus.AVAILABLE)
                .price(BigDecimal.valueOf(300000))
                .accommodationPeriod(LocalDateRange.parse("2024-02-01", "2024-02-03"))
                .build();

        Home home = new Home();

        // when(실행): 어떠한 함수를 실행하면
        Reservation reservation = home.reserve(member, reservationDate, accommodation);

        // then(검증): 어떠한 결과가 나와야 한다.
        assertThat(reservation.getMember()).isEqualTo(member);
    }

}