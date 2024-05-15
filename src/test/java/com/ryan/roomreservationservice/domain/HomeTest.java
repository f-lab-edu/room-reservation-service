package com.ryan.roomreservationservice.domain;

import com.ryan.roomreservationservice.domain.enums.AccommodationStatus;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.ZoneId;

import static org.assertj.core.api.Assertions.assertThat;

class HomeTest {

    @Test
    public void 예약하기() {
        // given(준비): 어떠한 데이터가 준비되었을 때
        String name = "Ryan";
        Member member = new Member(name);

        ZoneId zoneId = ZoneId.of("Asia/Seoul");
        String roomName = "그린룸";
        long price = 300000;

        Room room = new Room(zoneId, roomName, price);

        LocalDate start = LocalDate.parse("2024-02-01");
        LocalDate end = LocalDate.parse("2024-02-03");

        DateRange reservationDate = new DateRange(start, end);
        Accommodation accommodation = new Accommodation(room, AccommodationStatus.AVAILABLE);

        Home home = new Home();

        // when(실행): 어떠한 함수를 실행하면
        Reservation reservation = home.reserve(member, reservationDate, accommodation);

        // then(검증): 어떠한 결과가 나와야 한다.
        assertThat(reservation.getMember()).isEqualTo(member);
    }

}