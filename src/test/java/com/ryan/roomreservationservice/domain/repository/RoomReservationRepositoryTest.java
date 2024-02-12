package com.ryan.roomreservationservice.domain.repository;

import com.ryan.roomreservationservice.domain.DateRange;
import com.ryan.roomreservationservice.domain.Room;
import com.ryan.roomreservationservice.domain.RoomReservation;
import com.ryan.roomreservationservice.persistance.RoomJpaRepository;
import com.ryan.roomreservationservice.persistance.RoomReservationJpaRepository;
import com.ryan.roomreservationservice.util.enums.ReservationStatus;
import com.ryan.roomreservationservice.util.enums.RoomStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

import java.time.Instant;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ActiveProfiles("mysql")
@DataJpaTest
@TestPropertySource(locations = "classpath:application-mysql.yaml")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class RoomReservationRepositoryTest {

    @Autowired
    private RoomJpaRepository roomJpaRepository;
    @Autowired
    private RoomReservationJpaRepository roomReservationJpaRepository;

    @BeforeEach
    public void setup() {
        roomJpaRepository.save(
                Room.builder()
                        .roomId(1L)
                        .zoneCode("Asia/Seoul")
                        .roomName("그린룸")
                        .roomStatus(RoomStatus.EXPOSURE_POSSIBLE)
                        .roomSize("4")
                        .build()
        );
    }

    @Nested
    class 객실예약 {
        @Test
        public void 단일객실예약() {
            // given(준비): 어떠한 데이터가 준비되었을 때
            Room room = roomJpaRepository.findByRoomName("그린룸").orElseThrow();

            Instant reservationStartDate = Instant.parse("2024-03-03T15:00:00.000Z");
            Instant reservationEndDate = Instant.parse("2024-03-05T11:00:00.000Z");

            // when(실행): 어떠한 함수를 실행하면
            RoomReservation roomReservation = RoomReservation.builder()
                    .room(room)
                    .reservationStatus(ReservationStatus.PENDING)
                    .reservation(new DateRange(reservationStartDate, reservationEndDate))
                    .build();

            RoomReservation save = roomReservationJpaRepository.save(roomReservation);
            Optional<RoomReservation> findRoomReservation = roomReservationJpaRepository.findById(save.getRoomReservationId());

            // then(검증): 어떠한 결과가 나와야 한다.
            assertThat(findRoomReservation.get().getRoomReservationId()).isEqualTo(roomReservation.getRoomReservationId());
        }

        @Test
        public void 예약된_객실_퇴실시간의_맞추서_예약() {
            // given(준비): 어떠한 데이터가 준비되었을 때
            Room room = roomJpaRepository.findByRoomName("그린룸").orElseThrow();

            Instant reservationStartDate1 = Instant.parse("2024-03-03T15:00:00.000Z");
            Instant reservationEndDate1 = Instant.parse("2024-03-05T11:00:00.000Z");

            Instant reservationStartDate2 = Instant.parse("2024-03-05T15:00:00.000Z");
            Instant reservationEndDate2 = Instant.parse("2024-03-08T11:00:00.000Z");

            // when(실행): 어떠한 함수를 실행하면
            RoomReservation roomReservation1 = RoomReservation.builder()
                    .room(room)
                    .reservationStatus(ReservationStatus.PENDING)
                    .reservation(new DateRange(reservationStartDate1, reservationEndDate1))
                    .build();

            RoomReservation roomReservation2 = RoomReservation.builder()
                    .room(room)
                    .reservationStatus(ReservationStatus.PENDING)
                    .reservation(new DateRange(reservationStartDate2, reservationEndDate2))
                    .build();

            RoomReservation saved1 = roomReservationJpaRepository.save(roomReservation1);
            RoomReservation saved2 = roomReservationJpaRepository.save(roomReservation2);

            Optional<RoomReservation> findRoomReservation1 = roomReservationJpaRepository.findById(saved1.getRoomReservationId());
            Optional<RoomReservation> findRoomReservation2 = roomReservationJpaRepository.findById(saved2.getRoomReservationId());

            // then(검증): 어떠한 결과가 나와야 한다.
            assertThat(findRoomReservation1.get().getRoomReservationId()).isEqualTo(roomReservation1.getRoomReservationId());
            assertThat(findRoomReservation2.get().getRoomReservationId()).isEqualTo(roomReservation2.getRoomReservationId());
        }
    }

    @Nested
    class 중첩객실예약 {
        @Test
        public void 동일한_날짜의_중복객실_예약하기() {
            // given(준비): 어떠한 데이터가 준비되었을 때
            Room room = roomJpaRepository.findByRoomName("그린룸").orElseThrow();

            Instant reservationStartDate = Instant.parse("2024-03-03T15:00:00.000Z");
            Instant reservationEndDate = Instant.parse("2024-03-05T11:00:00.000Z");

            // when(실행): 어떠한 함수를 실행하면
            RoomReservation roomReservation1 = RoomReservation.builder()
                    .room(room)
                    .reservationStatus(ReservationStatus.PENDING)
                    .reservation(new DateRange(reservationStartDate, reservationEndDate))
                    .build();

            RoomReservation roomReservation2 = RoomReservation.builder()
                    .room(room)
                    .reservationStatus(ReservationStatus.PENDING)
                    .reservation(new DateRange(reservationStartDate, reservationEndDate))
                    .build();

            roomReservationJpaRepository.save(roomReservation1);

            // then(검증): 어떠한 결과가 나와야 한다.
            assertThrows(JpaSystemException.class, () ->
                    roomReservationJpaRepository.save(roomReservation2)
            );
        }

        @Test
        public void 예약종료_날짜가_중첩되는_상황() {
            // given(준비): 어떠한 데이터가 준비되었을 때
            Room room = roomJpaRepository.findByRoomName("그린룸").orElseThrow();

            Instant reservationStartDate1 = Instant.parse("2024-03-10T15:00:00.000Z");
            Instant reservationEndDate1 = Instant.parse("2024-03-15T11:00:00.000Z");

            // when(실행): 어떠한 함수를 실행하면
            RoomReservation roomReservation1 = RoomReservation.builder()
                    .room(room)
                    .reservationStatus(ReservationStatus.PENDING)
                    .reservation(new DateRange(reservationStartDate1, reservationEndDate1))
                    .build();

            Instant reservationStartDate2 = Instant.parse("2024-03-08T15:00:00.000Z");
            Instant reservationEndDate2 = Instant.parse("2024-03-13T11:00:00.000Z");

            RoomReservation roomReservation2 = RoomReservation.builder()
                    .room(room)
                    .reservationStatus(ReservationStatus.PENDING)
                    .reservation(new DateRange(reservationStartDate2, reservationEndDate2))
                    .build();

            roomReservationJpaRepository.save(roomReservation1);

            // then(검증): 어떠한 결과가 나와야 한다.
            assertThrows(JpaSystemException.class, () ->
                    roomReservationJpaRepository.save(roomReservation2)
            );
        }

        @Test
        public void 예약시작_날짜가_중첩되는_상황() {
            // given(준비): 어떠한 데이터가 준비되었을 때
            Room room = roomJpaRepository.findByRoomName("그린룸").orElseThrow();

            Instant reservationStartDate1 = Instant.parse("2024-03-10T15:00:00.000Z");
            Instant reservationEndDate1 = Instant.parse("2024-03-15T11:00:00.000Z");

            // when(실행): 어떠한 함수를 실행하면
            RoomReservation roomReservation1 = RoomReservation.builder()
                    .room(room)
                    .reservationStatus(ReservationStatus.PENDING)
                    .reservation(new DateRange(reservationStartDate1, reservationEndDate1))
                    .build();

            Instant reservationStartDate2 = Instant.parse("2024-03-12T15:00:00.000Z");
            Instant reservationEndDate2 = Instant.parse("2024-03-18T11:00:00.000Z");

            RoomReservation roomReservation2 = RoomReservation.builder()
                    .room(room)
                    .reservationStatus(ReservationStatus.PENDING)
                    .reservation(new DateRange(reservationStartDate2, reservationEndDate2))
                    .build();

            roomReservationJpaRepository.save(roomReservation1);

            // then(검증): 어떠한 결과가 나와야 한다.
            assertThrows(JpaSystemException.class, () ->
                    roomReservationJpaRepository.save(roomReservation2)
            );
        }

        @Test
        public void 예약된_날짜가_포함되는_상황() {
            // given(준비): 어떠한 데이터가 준비되었을 때
            Room room = roomJpaRepository.findByRoomName("그린룸").orElseThrow();

            Instant reservationStartDate1 = Instant.parse("2024-03-10T15:00:00.000Z");
            Instant reservationEndDate1 = Instant.parse("2024-03-15T11:00:00.000Z");

            // when(실행): 어떠한 함수를 실행하면
            RoomReservation roomReservation1 = RoomReservation.builder()
                    .room(room)
                    .reservationStatus(ReservationStatus.PENDING)
                    .reservation(new DateRange(reservationStartDate1, reservationEndDate1))
                    .build();

            Instant reservationStartDate2 = Instant.parse("2024-03-08T15:00:00.000Z");
            Instant reservationEndDate2 = Instant.parse("2024-03-18T11:00:00.000Z");

            RoomReservation roomReservation2 = RoomReservation.builder()
                    .room(room)
                    .reservationStatus(ReservationStatus.PENDING)
                    .reservation(new DateRange(reservationStartDate2, reservationEndDate2))
                    .build();

            roomReservationJpaRepository.save(roomReservation1);

            // then(검증): 어떠한 결과가 나와야 한다.
            assertThrows(JpaSystemException.class, () ->
                    roomReservationJpaRepository.save(roomReservation2)
            );
        }
    }
}