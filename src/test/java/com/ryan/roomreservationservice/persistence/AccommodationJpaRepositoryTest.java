package com.ryan.roomreservationservice.persistence;

import com.ryan.roomreservationservice.domain.Accommodation;
import com.ryan.roomreservationservice.domain.Room;
import com.ryan.roomreservationservice.util.enums.AccommodationAvailability;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

import java.time.Duration;
import java.time.Instant;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("memory")
@TestPropertySource(locations = "classpath:application-h2.yaml")
class AccommodationJpaRepositoryTest {

    @Autowired
    private RoomJpaRepository roomJpaRepository;

    @Autowired
    private AccommodationJpaRepository accommodationJpaRepository;

    @Test
    public void 예약일_기준으로_숙박정보_조회하기() {
        // given(준비): 어떠한 데이터가 준비되었을 때
        Room room = this.roomJpaRepository.findByRoomName("그린룸1")
                .orElseThrow(() -> new IllegalArgumentException());

        Instant start = Instant.now();
        Instant end = start.plus(Duration.ofDays(2));

        // when(실행): 어떠한 함수를 실행하면
        List<Accommodation> accommodationList = this.accommodationJpaRepository.findByRoomAndReservationDateWithPessimisticLock(room, start, end);

        // then(검증): 어떠한 결과가 나와야 한다.
        assertThat(accommodationList)
                .hasSize(2)
                .extracting("availability")
                .containsOnly(AccommodationAvailability.AVAILABLE);
    }
}