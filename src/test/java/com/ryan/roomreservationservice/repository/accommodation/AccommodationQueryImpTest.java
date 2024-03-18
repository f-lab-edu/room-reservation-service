package com.ryan.roomreservationservice.repository.accommodation;

import com.ryan.roomreservationservice.domain.Accommodation;
import com.ryan.roomreservationservice.domain.Room;
import com.ryan.roomreservationservice.persistence.AccommodationJpaRepository;
import com.ryan.roomreservationservice.persistence.RoomJpaRepository;
import com.ryan.roomreservationservice.util.enums.AccommodationAvailability;
import com.ryan.roomreservationservice.util.enums.CommonStatusCode;
import com.ryan.roomreservationservice.util.enums.ErrorType;
import com.ryan.roomreservationservice.util.exception.CommonException;
import com.ryan.roomreservationservice.util.exception.ErrorMessage;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.Instant;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("memory")
@TestPropertySource(locations = "classpath:application-h2.yaml")
class AccommodationQueryImpTest {

    @Autowired
    private RoomJpaRepository roomJpaRepository;
    @Autowired
    private AccommodationJpaRepository accommodationJpaRepository;

    @Test
    public void 테스트_데이터_등록확인() {
        // given(준비): 어떠한 데이터가 준비되었을 때
        List<Accommodation> accommodationList = this.accommodationJpaRepository.findAll();

        // when(실행): 어떠한 함수를 실행하면
        int size = accommodationList.size();

        // then(검증): 어떠한 결과가 나와야 한다.
        assertThat(size).isGreaterThan(0);
    }

    @Test
    @Transactional
    public void 객실예약대기_상태변경() {
        // given(준비): 어떠한 데이터가 준비되었을 때
        Room room = this.roomJpaRepository.findByRoomName("그린룸1")
                .orElseThrow(() -> new RuntimeException("객실이 존재하지 않습니다."));

        Instant start = Instant.now();
        Instant end = start.plus(Duration.ofDays(2));

        // when(실행): 어떠한 함수를 실행하면
        List<Accommodation> accommodationList = this.accommodationJpaRepository.findByRoomAndReservationDateWithPessimisticLock(room, start, end)
                .stream()
                .peek(accommodation -> {
                    if (!accommodation.isAvailableStatus()) throw CommonException.builder()
                            .errorType(ErrorType.DEVELOPER)
                            .status(CommonStatusCode.FAIL.getStatusCode())
                            .clientErrorMessage(ErrorMessage.RESERVATION_CURRENTLY_UNAVAILABLE)
                            .build();
                    accommodation.transitionToPending();
                }).toList();

        // then(검증): 어떠한 결과가 나와야 한다.
        List<Accommodation> saveAll = this.accommodationJpaRepository.saveAll(accommodationList);
        saveAll.stream().forEach(accommodation -> {
            assertThat(accommodation.getAvailability()).isEqualTo(AccommodationAvailability.PENDING);
        });
    }

}