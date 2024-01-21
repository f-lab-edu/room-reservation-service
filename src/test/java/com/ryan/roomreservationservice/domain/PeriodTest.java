package com.ryan.roomreservationservice.domain;

import com.ryan.roomreservationservice.util.exception.ErrorMessage;
import org.junit.jupiter.api.Test;

import java.time.Instant;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PeriodTest {

    @Test
    public void 시작날짜와_종료날짜를_설정_객체생성() {
        // given(준비): 어떠한 데이터가 준비되었을 때
        Instant start = Instant.parse("2023-02-01T15:00:00.000Z");
        Instant end = Instant.parse("2024-02-03T11:00:00.000Z");

        // when(실행): 어떠한 함수를 실행하면
        Period period = new Period(start, end);

        //then(검증): 어떠한 결과가 나와야 한다.
        assertThat(period).isNotNull();
    }

    @Test
    public void 시작날짜값을_NULL_값일때_에러발생() {
        // given(준비): 어떠한 데이터가 준비되었을 때
        Instant start = null;
        Instant end = Instant.parse("2024-02-03T11:00:00.000Z");

        // when(실행): 어떠한 함수를 실행하면
        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class, () -> new Period(start, end));

        // then(검증): 어떠한 결과가 나와야 한다.
        assertThat(illegalArgumentException.getMessage()).isEqualTo(ErrorMessage.CANNOT_BE_NULL_VALUE);
    }

    @Test
    public void 종료날짜값이_NULL_값일때_에러발생() {
        // given(준비): 어떠한 데이터가 준비되었을 때
        Instant start = Instant.parse("2024-02-01T11:00:00.000Z");
        Instant end = null;

        // when(실행): 어떠한 함수를 실행하면
        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class, () -> new Period(start, end));

        // then(검증): 어떠한 결과가 나와야 한다.
        assertThat(illegalArgumentException.getMessage()).isEqualTo(ErrorMessage.CANNOT_BE_NULL_VALUE);
    }

    @Test
    public void 시작날짜가_과거날짜로_설정이_되었다면_에러발생() {
        // given(준비): 어떠한 데이터가 준비되었을 때
        Instant start = Instant.parse("2024-01-01T11:00:00.000Z");
        Instant end = Instant.parse("2024-01-03T11:00:00.000Z");

        // when(실행): 어떠한 함수를 실행하면
        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class, () -> new Period(start, end));

        // then(검증): 어떠한 결과가 나와야 한다.
        assertThat(illegalArgumentException.getMessage()).isEqualTo(ErrorMessage.CANNOT_BE_SET_TO_PAST_DATE);
    }

    @Test
    public void 과거날짜는_시작날짜_보다_이전일_수_없습니다() {
        // given(준비): 어떠한 데이터가 준비되었을 때
        Instant start = Instant.parse("2024-02-01T11:00:00.000Z");
        Instant end = Instant.parse("2024-01-31T11:00:00.000Z");

        // when(실행): 어떠한 함수를 실행하면
        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class, () -> new Period(start, end));

        // then(검증): 어떠한 결과가 나와야 한다.
        assertThat(illegalArgumentException.getMessage()).isEqualTo(ErrorMessage.CANNOT_BE_EARLIER_THAN_THE_START_DATE);
    }
}