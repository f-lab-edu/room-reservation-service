package com.ryan.roomreservationservice.domain;

import com.ryan.roomreservationservice.util.enums.ReservationStatus;
import com.ryan.roomreservationservice.util.enums.RoomStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.time.LocalTime;
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
                .roomStatus(RoomStatus.EXPOSURE_POSSIBLE)
                .zoneCode("Asia/Seoul")
                .roomName("그린룸1")
                .build();

//        RoomReservation roomReservation = RoomReservation.builder()
//                .room(room)
//                .reservationStatus(ReservationStatus.AVAILABLE)
//                .reservation(new DateRange(
//                        Instant.parse("2024-01-17T15:00:00.000Z"),
//                        Instant.parse("2024-01-18T11:00:00.000Z")
//                ))
//                .build();


        // when(실행), then(검증)
        assertThat(room).isNotNull();
    }

}