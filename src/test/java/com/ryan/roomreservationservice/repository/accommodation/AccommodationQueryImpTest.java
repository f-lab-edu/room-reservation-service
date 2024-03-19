package com.ryan.roomreservationservice.repository.accommodation;

import com.ryan.roomreservationservice.domain.Accommodation;
import com.ryan.roomreservationservice.domain.DateRange;
import com.ryan.roomreservationservice.domain.Room;
import com.ryan.roomreservationservice.util.enums.AccommodationAvailability;
import com.ryan.roomreservationservice.util.enums.CommonStatusCode;
import com.ryan.roomreservationservice.util.enums.ErrorType;
import com.ryan.roomreservationservice.util.enums.RoomStatus;
import com.ryan.roomreservationservice.util.exception.CommonException;
import com.ryan.roomreservationservice.util.exception.ErrorMessage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.Instant;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@DataJpaTest
@ActiveProfiles("memory")
@TestPropertySource(locations = "classpath:application-h2.yaml")
@ExtendWith(MockitoExtension.class)
class AccommodationQueryImpTest {

    @Mock
    private AccommodationQueryImp accommodationQueryImp;

    @Test
    @Transactional
    public void 객실예약대기_상태변경() {
        // given(준비): 어떠한 데이터가 준비되었을 때
        Room room = Room.builder()
                .roomName("그린룸1")
                .roomSize("4")
                .roomStatus(RoomStatus.EXPOSURE_POSSIBLE)
                .zoneCode("Asia/Seoul")
                .build();

        Instant start = Instant.now();
        Instant end = start.plus(Duration.ofDays(2));

        DateRange dateRange = new DateRange(start, end);

        // when(실행): 어떠한 함수를 실행하면
        when(this.accommodationQueryImp.findByRoomAndReservationDateWithPessimisticLock(room, dateRange))
                .thenReturn(List.of(
                        Accommodation.builder()
                                .room(room)
                                .availability(AccommodationAvailability.AVAILABLE)
                                .reservationDate(start)
                                .build(),
                        Accommodation.builder()
                                .room(room)
                                .availability(AccommodationAvailability.AVAILABLE)
                                .reservationDate(end)
                                .build()
                ));

        List<Accommodation> accommodationList = this.accommodationQueryImp.findByRoomAndReservationDateWithPessimisticLock(room, dateRange)
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
        accommodationList.stream()
                .forEach(accommodation -> {
                    assertThat(accommodation.getAvailability()).isEqualTo(AccommodationAvailability.PENDING);
                });
    }

}