package com.ryan.roomreservationservice.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

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
        Room room = Room.builder().roomId(1L)
                .roomStatus("노출가능")
                .roomName("그린룸1")
                .minimumNumberOfPeople(4)
                .maximumNumberOfPeople(8)
                .checkInTime(LocalTime.of(15, 00))
                .checkOutTime(LocalTime.of(11, 00))
                .roomType(Arrays.asList("원룸형", "온돌형"))
                .roomStructure(Arrays.asList("원룸형", "화장실"))
                .furniture(Arrays.asList("침대", "화장대", "테이블", "식탁"))
                .electronics(Arrays.asList("홈시어터", "빔프로젝트", "TV"))
                .convenienceTools(Arrays.asList("커피포트", "정수기", "세면도구"))
                .roomTheme(Arrays.asList("온돌", "월풀스파"))
                .roomDescription("객실 설명입니다.")
                .build();

        RoomReservation roomReservation = RoomReservation.builder()
                .roomReservationId(room.getRoomId())
                .reservationStatus("PENDING")
                .reservationStartDate(LocalDateTime.parse("2024-01-17 00:00:00", dateTimeFormatter))
                .reservationEndDate(LocalDateTime.parse("2024-01-24 00:00:00", dateTimeFormatter))
                .checkInTime(LocalTime.of(15, 00))
                .checkOutTime(LocalTime.of(11, 00))
                .numberOfAdults(2)
                .numberOfChildren(1)
                .numberOfInfants(0)
                .defaultRoomPrice(70000)
                .totalPricePaid(70000)
                .reservationPerson("Ryan")
                .reservationPersonPhone("01012345678")
                .emergencyPhone("01087654321")
                .email("ryan@mgail.com")
                .estimatedTimeOfArrival(LocalDateTime.parse("2024-01-24 15:00:00", dateTimeFormatter))
                .reservationRequest("요청사항 없음")
                .funnels("사이트")
                .build();

        room.addRoomReservation(roomReservation);

        // when(실행), then(검증)
        assertThat(room).isNotNull();
    }

}