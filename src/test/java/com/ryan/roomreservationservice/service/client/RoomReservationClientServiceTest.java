package com.ryan.roomreservationservice.service.client;

import com.ryan.roomreservationservice.controller.client.dto.request.ReserveRequestDto;
import com.ryan.roomreservationservice.repository.RoomRepository;
import com.ryan.roomreservationservice.repository.RoomReservationRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Instant;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class RoomReservationClientServiceTest {

    @InjectMocks
    private RoomReservationClientService roomReservationClientService;

    @Mock
    private RoomReservationRepository roomReservationRepository;

    @Mock
    private RoomRepository roomRepository;

    @Test
    public void 예약하기() {
        // given(준비): 어떠한 데이터가 준비되었을 때
        Instant reservationStartDate = Instant.parse("2024-02-03T11:00:00.000Z");
        Instant reservationEndDate = Instant.parse("2024-02-05T11:00:00.000Z");

        ReserveRequestDto reserveRequestDto = new ReserveRequestDto();
        reserveRequestDto.setRoomName("그린룸");
        reserveRequestDto.setReservationStartDate(reservationStartDate);
        reserveRequestDto.setReservationEndDate(reservationEndDate);

        // when(실행): 어떠한 함수를 실행하면
        boolean result = roomReservationClientService.reserve(reserveRequestDto);

        // then(검증): 어떠한 결과가 나와야 한다.
        assertThat(result).isTrue();
    }
}