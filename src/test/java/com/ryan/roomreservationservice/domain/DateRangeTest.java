package com.ryan.roomreservationservice.domain;

import com.ryan.roomreservationservice.utils.exception.ErrorMessage;
import org.junit.jupiter.api.Test;

import java.time.Instant;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DateRangeTest {

    @Test
    public void 올바른_날짜_생성() {
        // given(준비): 어떠한 데이터가 준비되었을 때
        Instant start = Instant.parse("2024-02-01T15:00:00.000Z");
        Instant end = Instant.parse("2024-02-03T11:00:00.000Z");

        // when(실행): 어떠한 함수를 실행하면
        DateRange dateRange = new DateRange(start, end);

        // then(검증): 어떠한 결과가 나와야 한다.
        assertThat(dateRange).isNotNull();
    }

    @Test
    public void null_값을_통한_생성() {
        // given(준비): 어떠한 데이터가 준비되었을 때
        Instant start = null;
        Instant end = null;

        // when(실행): 어떠한 함수를 실행하면
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new DateRange(start, end));

        // then(검증): 어떠한 결과가 나와야 한다.
        assertThat(exception.getMessage()).isEqualTo(ErrorMessage.CANNOT_BE_NULL_VALUE);
    }

    @Test
    public void 종료일이_시작일보다_날짜가_이전일때() {
        // given(준비): 어떠한 데이터가 준비되었을 때
        Instant start = Instant.parse("2024-02-04T15:00:00.000Z");
        Instant end = Instant.parse("2024-02-02T11:00:00.000Z");

        // when(실행): 어떠한 함수를 실행하면
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new DateRange(start, end));

        // then(검증): 어떠한 결과가 나와야 한다.
        assertThat(exception.getMessage()).isEqualTo(ErrorMessage.CANNOT_BE_EARLIER_THAN_THE_START_DATE);
    }

    @Test
    public void 일박_날짜를_예약시_검증() {
        // given(준비): 어떠한 데이터가 준비되었을 때
        Instant start = Instant.parse("2024-02-01T15:00:00.000Z");
        Instant end = Instant.parse("2024-02-02T11:00:00.000Z");

        DateRange dateRange = new DateRange(start, end);

        // when(실행): 어떠한 함수를 실행하면
        long day = dateRange.calculateDayPeriod();

        // then(검증): 어떠한 결과가 나와야 한다.
        assertThat(day).isEqualTo(1);
    }

    @Test
    public void 이박_날짜_예약시_검증() {
        // given(준비): 어떠한 데이터가 준비되었을 때
        Instant start = Instant.parse("2024-02-01T15:00:00.000Z");
        Instant end = Instant.parse("2024-02-03T11:00:00.000Z");

        DateRange dateRange = new DateRange(start, end);

        // when(실행): 어떠한 함수를 실행하면
        long day = dateRange.calculateDayPeriod();

        // then(검증): 어떠한 결과가 나와야 한다.
        assertThat(day).isEqualTo(2);
    }

    @Test
    public void 삼십일_날짜_검증() {
        // given(준비): 어떠한 데이터가 준비되었을 때
        Instant start = Instant.parse("2024-02-01T15:00:00.000Z");
        Instant end = Instant.parse("2024-03-02T11:00:00.000Z");

        DateRange dateRange = new DateRange(start, end);

        // when(실행): 어떠한 함수를 실행하면
        long day = dateRange.calculateDayPeriod();

        // then(검증): 어떠한 결과가 나와야 한다.
        assertThat(day).isEqualTo(30);
    }

    @Test
    public void 시작_시간이_잘못된_경우_검증() {
        // given(준비): 어떠한 데이터가 준비되었을 때
        Instant start = Instant.parse("2024-02-01T16:00:00.000Z");
        Instant end = Instant.parse("2024-03-02T11:00:00.000Z");

        // when(실행): 어떠한 함수를 실행하면
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new DateRange(start, end));

        // then(검증): 어떠한 결과가 나와야 한다.
        assertThat(exception.getMessage()).isEqualTo(ErrorMessage.CHECK_AVAILABLE_TIME_FOR_RESERVATION);
    }

    @Test
    public void 종료_시간이_잘못된_경우_검증() {
        // given(준비): 어떠한 데이터가 준비되었을 때
        Instant start = Instant.parse("2024-02-01T15:00:00.000Z");
        Instant end = Instant.parse("2024-03-02T12:00:00.000Z");

        // when(실행): 어떠한 함수를 실행하면
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new DateRange(start, end));

        // then(검증): 어떠한 결과가 나와야 한다.
        assertThat(exception.getMessage()).isEqualTo(ErrorMessage.CHECK_AVAILABLE_TIME_FOR_RESERVATION);
    }

}