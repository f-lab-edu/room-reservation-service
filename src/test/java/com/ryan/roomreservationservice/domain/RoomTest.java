package com.ryan.roomreservationservice.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

import static org.assertj.core.api.Assertions.assertThat;

class RoomTest {

    DateTimeFormatter dateTimeFormatter;

    @BeforeEach
    void init() {
        dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    }

    @Test
    void 객실생성() {
        // given(준비): 어떠한 데이터가 준비되었을 때
        Room room = Room.builder()
                .roomId(1L)
                .roomStatus("노출가능")
                .zoneId("Asia/Seoul")
                .roomName("그린룸1")
                .build();

        RoomReservation roomReservation = RoomReservation.builder()
                .roomReservationId(room.getRoomId())
                .reservationStatus("PENDING")
                .reservationDate(new DateRange(
                        Instant.parse("2024-01-17T15:00:00.000Z"),
                        Instant.parse("2024-01-18T11:00:00.000Z")
                ))
                .checkInOut(new TimeRange(
                        LocalTime.of(15, 0),
                        LocalTime.of(11, 0)
                ))
                .build();

        room.addRoomReservation(roomReservation);

        // when(실행), then(검증)
        assertThat(room).isNotNull();
    }

}