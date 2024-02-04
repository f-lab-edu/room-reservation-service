package com.ryan.roomreservationservice.domain;

import com.ryan.roomreservationservice.util.exception.ErrorMessage;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.Period;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DateRangeTest {

    @Test
    public void 시작날짜와_종료날짜를_설정_객체생성() {
        // given(준비): 어떠한 데이터가 준비되었을 때
        Instant start = Instant.parse("2023-02-01T15:00:00.000Z");
        Instant end = Instant.parse("2024-02-03T11:00:00.000Z");

        // when(실행): 어떠한 함수를 실행하면
        DateRange dateRange = new DateRange(start, end);

        //then(검증): 어떠한 결과가 나와야 한다.
        assertThat(dateRange).isNotNull();
    }

    @Test
    public void 시작날짜값을_NULL_값일때_에러발생() {
        // given(준비): 어떠한 데이터가 준비되었을 때
        Instant start = null;
        Instant end = Instant.parse("2024-02-03T11:00:00.000Z");

        // when(실행): 어떠한 함수를 실행하면
        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class, () -> new DateRange(start, end));

        // then(검증): 어떠한 결과가 나와야 한다.
        assertThat(illegalArgumentException.getMessage()).isEqualTo(ErrorMessage.CANNOT_BE_NULL_VALUE);
    }

    @Test
    public void 종료날짜값이_NULL_값일때_에러발생() {
        // given(준비): 어떠한 데이터가 준비되었을 때
        Instant start = Instant.parse("2024-02-01T11:00:00.000Z");
        Instant end = null;

        // when(실행): 어떠한 함수를 실행하면
        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class, () -> new DateRange(start, end));

        // then(검증): 어떠한 결과가 나와야 한다.
        assertThat(illegalArgumentException.getMessage()).isEqualTo(ErrorMessage.CANNOT_BE_NULL_VALUE);
    }

    @Test
    public void 시작날짜가_과거날짜로_설정이_되었다면_에러발생() {
        // given(준비): 어떠한 데이터가 준비되었을 때
        Instant start = Instant.parse("2024-01-01T11:00:00.000Z");
        Instant end = Instant.parse("2024-01-03T11:00:00.000Z");

        // when(실행): 어떠한 함수를 실행하면
        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class, () -> new DateRange(start, end));

        // then(검증): 어떠한 결과가 나와야 한다.
        assertThat(illegalArgumentException.getMessage()).isEqualTo(ErrorMessage.CANNOT_BE_SET_TO_PAST_DATE);
    }

    @Test
    public void 과거날짜는_시작날짜_보다_이전일_수_없습니다() {
        // given(준비): 어떠한 데이터가 준비되었을 때
        Instant start = Instant.parse("2024-02-01T11:00:00.000Z");
        Instant end = Instant.parse("2024-01-31T11:00:00.000Z");

        // when(실행): 어떠한 함수를 실행하면
        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class, () -> new DateRange(start, end));

        // then(검증): 어떠한 결과가 나와야 한다.
        assertThat(illegalArgumentException.getMessage()).isEqualTo(ErrorMessage.CANNOT_BE_EARLIER_THAN_THE_START_DATE);
    }

    @Test
    public void 날짜가_겹치지_않을때() {
        // given(준비): 어떠한 데이터가 준비되었을 때
        Instant periodDate1 = Instant.parse("2024-02-01T11:00:00.000Z");
        Instant periodDate2 = Instant.parse("2024-02-10T11:00:00.000Z");

        DateRange period1 = new DateRange(periodDate1, periodDate1.plus(Period.ofDays(1)));
        DateRange period2 = new DateRange(periodDate2, periodDate2.plus(Period.ofDays(3)));

        // when(실행): 어떠한 함수를 실행하면
        boolean checkAvailabilityPeriod = period1.checkAvailabilityPeriod(period2);

        // then(검증): 어떠한 결과가 나와야 한다.
        assertThat(checkAvailabilityPeriod).isTrue();
    }

    @Test
    public void 시작일이_같을때() {
        // given(준비): 어떠한 데이터가 준비되었을 때
        Instant periodDate1 = Instant.parse("2024-02-01T11:00:00.000Z");
        Instant periodDate2 = Instant.parse("2024-02-01T11:00:00.000Z");

        DateRange period1 = new DateRange(periodDate1, periodDate1.plus(Period.ofDays(1)));
        DateRange period2 = new DateRange(periodDate2, periodDate2.plus(Period.ofDays(3)));

        // when(실행): 어떠한 함수를 실행하면
        boolean checkAvailabilityPeriod = period1.checkAvailabilityPeriod(period2);

        // then(검증): 어떠한 결과가 나와야 한다.
        assertThat(checkAvailabilityPeriod).isFalse();
    }

    @Test
    public void 종료일이_같을때() {
        // given(준비): 어떠한 데이터가 준비되었을 때
        Instant periodDate1 = Instant.parse("2024-02-03T11:00:00.000Z");
        Instant periodDate2 = Instant.parse("2024-02-01T11:00:00.000Z");

        DateRange period1 = new DateRange(periodDate1, periodDate1.plus(Period.ofDays(1)));
        DateRange period2 = new DateRange(periodDate2, periodDate2.plus(Period.ofDays(3)));

        // when(실행): 어떠한 함수를 실행하면
        boolean checkAvailabilityPeriod = period1.checkAvailabilityPeriod(period2);

        // then(검증): 어떠한 결과가 나와야 한다.
        assertThat(checkAvailabilityPeriod).isFalse();
    }

    @Test
    public void 시작날짜_기준으로_날짜가_겹칠때() {
        // given(준비): 어떠한 데이터가 준비되었을 때
        Instant periodDate1 = Instant.parse("2024-02-03T11:00:00.000Z");
        Instant periodDate2 = Instant.parse("2024-02-01T11:00:00.000Z");

        DateRange period1 = new DateRange(periodDate1, periodDate1.plus(Period.ofDays(3)));
        DateRange period2 = new DateRange(periodDate2, periodDate2.plus(Period.ofDays(3)));

        // when(실행): 어떠한 함수를 실행하면
        boolean checkAvailabilityPeriod = period1.checkAvailabilityPeriod(period2);

        // then(검증): 어떠한 결과가 나와야 한다.
        assertThat(checkAvailabilityPeriod).isFalse();
    }

    @Test
    public void 종료날짜_기준으로_날짜가_겹칠때() {
        // given(준비): 어떠한 데이터가 준비되었을 때
        Instant periodDate1 = Instant.parse("2024-02-03T11:00:00.000Z");
        Instant periodDate2 = Instant.parse("2024-02-05T11:00:00.000Z");

        DateRange period1 = new DateRange(periodDate1, periodDate1.plus(Period.ofDays(3)));
        DateRange period2 = new DateRange(periodDate2, periodDate2.plus(Period.ofDays(3)));

        // when(실행): 어떠한 함수를 실행하면
        boolean checkAvailabilityPeriod = period1.checkAvailabilityPeriod(period2);

        // then(검증): 어떠한 결과가 나와야 한다.
        assertThat(checkAvailabilityPeriod).isFalse();
    }
}