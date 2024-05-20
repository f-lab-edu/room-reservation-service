package com.ryan.roomreservationservice.domain;

import com.ryan.roomreservationservice.utils.exception.ErrorMessage;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class LocalDateRangeTest {

    @Test
    public void 올바른_날짜_생성() {
        // given(준비): 어떠한 데이터가 준비되었을 때
        LocalDate start = LocalDate.parse("2024-02-01");
        LocalDate end = LocalDate.parse("2024-02-03");

        // when(실행): 어떠한 함수를 실행하면
        LocalDateRange localDateRange = new LocalDateRange(start, end);

        // then(검증): 어떠한 결과가 나와야 한다.
        assertThat(localDateRange).isNotNull();
    }

    @Test
    public void null_값을_통한_생성() {
        // given(준비): 어떠한 데이터가 준비되었을 때
        LocalDate start = null;
        LocalDate end = null;

        // when(실행): 어떠한 함수를 실행하면
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new LocalDateRange(start, end));

        // then(검증): 어떠한 결과가 나와야 한다.
        assertThat(exception.getMessage()).isEqualTo(ErrorMessage.CANNOT_BE_NULL_VALUE);
    }

    @Test
    public void 종료일이_시작일보다_날짜가_이전일때() {
        // given(준비): 어떠한 데이터가 준비되었을 때
        LocalDate start = LocalDate.parse("2024-02-04");
        LocalDate end = LocalDate.parse("2024-02-02");

        // when(실행): 어떠한 함수를 실행하면
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new LocalDateRange(start, end));

        // then(검증): 어떠한 결과가 나와야 한다.
        assertThat(exception.getMessage()).isEqualTo(ErrorMessage.CANNOT_BE_EARLIER_THAN_THE_START_DATE);
    }

    @Test
    public void 일박_날짜를_예약시_검증() {
        // given(준비): 어떠한 데이터가 준비되었을 때
        LocalDate start = LocalDate.parse("2024-02-01");
        LocalDate end = LocalDate.parse("2024-02-02");

        LocalDateRange localDateRange = new LocalDateRange(start, end);

        // when(실행): 어떠한 함수를 실행하면
        long day = localDateRange.calculateDayPeriod();

        // then(검증): 어떠한 결과가 나와야 한다.
        assertThat(day).isEqualTo(1);
    }

    @Test
    public void 이박_날짜_예약시_검증() {
        // given(준비): 어떠한 데이터가 준비되었을 때
        LocalDate start = LocalDate.parse("2024-02-01");
        LocalDate end = LocalDate.parse("2024-02-03");

        LocalDateRange localDateRange = new LocalDateRange(start, end);

        // when(실행): 어떠한 함수를 실행하면
        long day = localDateRange.calculateDayPeriod();

        // then(검증): 어떠한 결과가 나와야 한다.
        assertThat(day).isEqualTo(2);
    }

    @Test
    public void 삼십일_날짜_검증() {
        // given(준비): 어떠한 데이터가 준비되었을 때
        LocalDate start = LocalDate.parse("2024-02-01");
        LocalDate end = LocalDate.parse("2024-03-02");

        LocalDateRange localDateRange = new LocalDateRange(start, end);

        // when(실행): 어떠한 함수를 실행하면
        long day = localDateRange.calculateDayPeriod();

        // then(검증): 어떠한 결과가 나와야 한다.
        assertThat(day).isEqualTo(30);
    }

    @Test
    public void 시작일_삼일전_기간_검증() {
        // given(준비): 어떠한 데이터가 준비되었을 때
        LocalDate beforeDate = LocalDate.parse("2024-01-29");

        LocalDate start = LocalDate.parse("2024-02-01");
        LocalDate end = LocalDate.parse("2024-03-02");
        LocalDateRange localDateRange = new LocalDateRange(start, end);

        // when(실행): 어떠한 함수를 실행하면
        long period = localDateRange.calculatePeriodBeforeStartDate(beforeDate);

        // then(검증): 어떠한 결과가 나와야 한다.
        assertThat(period).isEqualTo(3);
    }

}