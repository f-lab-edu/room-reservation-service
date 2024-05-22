package com.ryan.roomreservationservice.domain;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;

import static org.assertj.core.api.Assertions.assertThat;

class RoomTest {

    @Test
    public void 예약일로부터_칠일이상_날짜_환불요청_계산하기() {
        // given(준비): 어떠한 데이터가 준비되었을 때
        ZoneId zoneId = ZoneId.of("Asia/Seoul");
        String name = "그린룸";
        BigDecimal price = BigDecimal.valueOf(300000);

        Room room = new Room(zoneId, name, price);

        LocalDate localDate = LocalDate.of(2024, 5, 12);
        LocalDateRange localDateRange = new LocalDateRange(LocalDate.of(2024, 5, 19), LocalDate.of(2024, 5, 20));

        // when(실행): 어떠한 함수를 실행하면
        BigDecimal refundAmount = room.calculateRoomRefundAmount(localDate, localDateRange);

        // then(검증): 어떠한 결과가 나와야 한다.
        assertThat(refundAmount).isEqualTo(BigDecimal.valueOf(300000));
    }

    @Test
    public void 예약일로부터_사일에서_육일사이_환불요청_계산하기() {
        // given(준비): 어떠한 데이터가 준비되었을 때
        ZoneId zoneId = ZoneId.of("Asia/Seoul");
        String name = "그린룸";
        BigDecimal price = BigDecimal.valueOf(300000);

        Room room = new Room(zoneId, name, price);

        LocalDate localDate = LocalDate.of(2024, 5, 15);
        LocalDateRange localDateRange = new LocalDateRange(LocalDate.of(2024, 5, 19), LocalDate.of(2024, 5, 20));

        // when(실행): 어떠한 함수를 실행하면
        BigDecimal refundAmount = room.calculateRoomRefundAmount(localDate, localDateRange);

        // then(검증): 어떠한 결과가 나와야 한다.
        assertThat(refundAmount).isEqualTo(BigDecimal.valueOf(210000));
    }
}