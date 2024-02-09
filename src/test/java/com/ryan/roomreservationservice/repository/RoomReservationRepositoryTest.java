package com.ryan.roomreservationservice.repository;

import com.ryan.roomreservationservice.domain.DateRange;
import com.ryan.roomreservationservice.domain.Room;
import com.ryan.roomreservationservice.domain.RoomReservation;
import com.ryan.roomreservationservice.persistance.RoomJpaRepository;
import com.ryan.roomreservationservice.persistance.RoomReservationJpaRepository;
import com.ryan.roomreservationservice.util.enums.ReservationStatus;
import com.ryan.roomreservationservice.util.enums.RoomStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;
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
        this.roomJpaRepository.save(
                Room.builder()
                        .roomId(1L)
                        .zoneCode("Asia/Seoul")
                        .roomName("그린룸")
                        .roomStatus(RoomStatus.EXPOSURE_POSSIBLE)
                        .roomSize("4")
                        .build()
        );
    }

    @Test
    public void 객실예약하기() {
        // given(준비): 어떠한 데이터가 준비되었을 때
        Room room = this.roomJpaRepository.findByRoomName("그린룸").orElseThrow();

        Instant reservationStartDate = Instant.parse("2024-02-03T11:00:00.000Z");
        Instant reservationEndDate = Instant.parse("2024-02-05T11:00:00.000Z");

        // when(실행): 어떠한 함수를 실행하면
        RoomReservation roomReservation = RoomReservation.builder()
                .room(room)
                .reservationStatus(ReservationStatus.PENDING)
                .reservation(new DateRange(reservationStartDate, reservationEndDate))
                .build();

        RoomReservation save = this.roomReservationJpaRepository.save(roomReservation);
        Optional<RoomReservation> findRoomReservation = this.roomReservationJpaRepository.findById(save.getRoomReservationId());

        // then(검증): 어떠한 결과가 나와야 한다.
        assertThat(findRoomReservation.get().getRoomReservationId()).isEqualTo(roomReservation.getRoomReservationId());
    }

    @Test
    public void 동일한_날짜의_중복객실_예약하기() {
        // given(준비): 어떠한 데이터가 준비되었을 때
        Room room = this.roomJpaRepository.findByRoomName("그린룸").orElseThrow();

        Instant reservationStartDate = Instant.parse("2024-02-03T11:00:00.000Z");
        Instant reservationEndDate = Instant.parse("2024-02-05T11:00:00.000Z");

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

        this.roomReservationJpaRepository.save(roomReservation1);

        // then(검증): 어떠한 결과가 나와야 한다.
        assertThrows(DataIntegrityViolationException.class, () ->
                this.roomReservationJpaRepository.save(roomReservation2)
        );
    }
}