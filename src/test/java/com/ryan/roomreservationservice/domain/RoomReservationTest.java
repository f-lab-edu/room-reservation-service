package com.ryan.roomreservationservice.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Instant;

import static org.assertj.core.api.Assertions.assertThat;

class RoomReservationTest {
    private Room room;

    @BeforeEach
    void init() {
        this.room = Room.builder().roomId(1L)
                .roomStatus("노출가능")
                .roomName("그린룸1")
                .build();
    }

    @Test
    void 객실예약성공() {
        // given(준비): 어떠한 데이터가 준비되었을 때
        RoomReservation roomReservation = RoomReservation.builder()
                .roomReservationId(room.getRoomId())
                .reservationStatus("PENDING")
                .dateRange(DateRange.builder()
                        .startDate(Instant.parse("2024-01-17T15:00:00.000Z"))
                        .endDate(Instant.parse("2024-01-18T11:00:00.000Z"))
                        .build())
                .build();

        Instant checkInDate = Instant.parse("2024-01-18T15:00:00.000Z");
        Instant checkOutDate = Instant.parse("2024-01-20T11:00:00.000Z");

        // when(실행): 어떠한 함수를 실행하면
        boolean checkDate = roomReservation.checkAvailabilityStatus(checkInDate, checkOutDate);

        // then(검증): 어떠한 결과가 나와야 한다.
        assertThat(checkDate).isTrue();
    }

    @Test
    void 이전년도_객실예약_진행() {
        // given(준비): 어떠한 데이터가 준비되었을 때
        RoomReservation roomReservation = RoomReservation.builder()
                .roomReservationId(room.getRoomId())
                .reservationStatus("PENDING")
                .dateRange(DateRange.builder()
                        .startDate(Instant.parse("2024-01-17T15:00:00.000Z"))
                        .endDate(Instant.parse("2024-01-18T11:00:00.000Z"))
                        .build())
                .build();

        Instant checkInDate = Instant.parse("2023-01-18T15:00:00.000Z");
        Instant checkOutDate = Instant.parse("2024-01-20T11:00:00.000Z");

        // when(실행): 어떠한 함수를 실행하면
        boolean checkDate = roomReservation.checkAvailabilityStatus(checkInDate, checkOutDate);

        // then(검증): 어떠한 결과가 나와야 한다.
        assertThat(checkDate).isFalse();
    }

    @Test
    public void 예약된_객실의_체크인날짜와_새로예약한_객실의_체크아웃날짜가_겹칠때() {
        // given(준비): 어떠한 데이터가 준비되었을 때
        RoomReservation roomReservation = RoomReservation.builder()
                .roomReservationId(room.getRoomId())
                .reservationStatus("PENDING")
                .dateRange(DateRange.builder()
                        .startDate(Instant.parse("2024-01-20T15:00:00.000Z"))
                        .endDate(Instant.parse("2024-01-25T11:00:00.000Z"))
                        .build())
                .build();

        Instant checkInDate = Instant.parse("2024-01-18T15:00:00.000Z");
        Instant checkOutDate = Instant.parse("2024-01-21T11:00:00.000Z");

        // when(실행): 어떠한 함수를 실행하면
        boolean result = roomReservation.checkAvailabilityStatus(checkInDate, checkOutDate);

        // then(검증): 어떠한 결과가 나와야 한다.
        assertThat(result).isFalse();
    }

    @Test
    public void 예약된_객실의_체크아웃날짜와_새로예약한_객실의_체크인날짜가_겹칠때() {
        // given(준비): 어떠한 데이터가 준비되었을 때
        RoomReservation roomReservation = RoomReservation.builder()
                .roomReservationId(room.getRoomId())
                .reservationStatus("PENDING")
                .dateRange(DateRange.builder()
                        .startDate(Instant.parse("2024-01-20T15:00:00.000Z"))
                        .endDate(Instant.parse("2024-01-25T11:00:00.000Z"))
                        .build())
                .build();

        Instant checkInDate = Instant.parse("2024-01-24T15:00:00.000Z");
        Instant checkOutDate = Instant.parse("2024-01-28T11:00:00.000Z");

        // when(실행): 어떠한 함수를 실행하면
        boolean result = roomReservation.checkAvailabilityStatus(checkInDate, checkOutDate);

        // then(검증): 어떠한 결과가 나와야 한다.
        assertThat(result).isFalse();
    }

}