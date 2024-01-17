package com.ryan.roomreservationservice.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.*;

class RoomReservationTest {

    private DateTimeFormatter dateTimeFormatter;
    private Room room;
   private RoomReservation roomReservation;

    @BeforeEach
    void init() {
        dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        this.room = Room.builder().roomId(1L)
                .roomStatus("노출가능")
                .roomName("그린룸1")
                .minimumNumberOfPeople(4)
                .maximumNumberOfPeople(8)
                .checkInTime(LocalTime.of(15, 0))
                .checkOutTime(LocalTime.of(11, 0))
                .roomType(Arrays.asList("원룸형", "온돌형"))
                .roomStructure(Arrays.asList("원룸형", "화장실"))
                .furniture(Arrays.asList("침대", "화장대", "테이블", "식탁"))
                .electronics(Arrays.asList("홈시어터", "빔프로젝트", "TV"))
                .convenienceTools(Arrays.asList("커피포트", "정수기", "세면도구"))
                .roomTheme(Arrays.asList("온돌", "월풀스파"))
                .roomDescription("객실 설명입니다.")
                .build();
    }

    @Test
    void 객실예약성공() {
        // given(준비): 어떠한 데이터가 준비되었을 때

        this.roomReservation = RoomReservation.builder()
                .roomReservationId(room.getRoomId())
                .reservationStatus("PENDING")
                .reservationStartDate(LocalDateTime.parse("2024-01-17 15:00:00", dateTimeFormatter))
                .reservationEndDate(LocalDateTime.parse("2024-01-18 11:00:00", dateTimeFormatter))
                .checkInTime(LocalTime.of(LocalDateTime.parse("2024-01-17 00:00:00", dateTimeFormatter).getDayOfMonth(),
                        LocalDateTime.parse("2024-01-17 00:00:00", dateTimeFormatter).getMinute()))
                .checkOutTime(LocalTime.of(LocalDateTime.parse("2024-01-18 11:00:00", dateTimeFormatter).getDayOfMonth(),
                        LocalDateTime.parse("2024-01-18 11:00:00", dateTimeFormatter).getMinute()))
                .numberOfAdults(2)
                .numberOfChildren(1)
                .numberOfInfants(0)
                .defaultRoomPrice(70000)
                .totalPricePaid(70000)
                .reservationPerson("Ryan")
                .reservationPersonPhone("01012345678")
                .emergencyPhone("01087654321")
                .email("ryan@mgail.com")
                .estimatedTimeOfArrival(LocalDateTime.parse("2024-01-17 15:00:00", dateTimeFormatter))
                .reservationRequest("요청사항 없음")
                .funnels("사이트")
                .build();

        LocalDateTime checkInDate = LocalDateTime.parse("2024-01-18 15:00:00", dateTimeFormatter);
        LocalDateTime checkOutDate = LocalDateTime.parse("2024-01-20 11:00:00", dateTimeFormatter);

        // when(실행): 어떠한 함수를 실행하면
        Boolean checkDate = this.roomReservation.checkAvailabilityStatus(checkInDate, checkOutDate);

        // then(검증): 어떠한 결과가 나와야 한다.
        assertThat(checkDate).isTrue();
    }

    @Test
    void 이전년도_객실예약_진행(){
        // given(준비): 어떠한 데이터가 준비되었을 때
        LocalDateTime checkInDate = LocalDateTime.parse("2023-01-18 15:00:00", dateTimeFormatter);
        LocalDateTime checkOutDate = LocalDateTime.parse("2024-01-20 11:00:00", dateTimeFormatter);

        // when(실행): 어떠한 함수를 실행하면
        Boolean checkDate = this.roomReservation.checkAvailabilityStatus(checkInDate, checkOutDate);

        // then(검증): 어떠한 결과가 나와야 한다.
        assertThat(checkDate).isFalse();
    }

}