package com.ryan.roomreservationservice.service.reservation;

import com.ryan.roomreservationservice.controller.reservation.ReservationDto;
import com.ryan.roomreservationservice.service.room.RoomQuery;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Instant;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class RoomReservationServiceImpTest {

    @InjectMocks
    private RoomReservationServiceImp reservationService;

    @Mock
    private ReservationQuery reservationQuery;
    @Mock
    private ReservationStore reservationStore;
    @Mock
    private RoomQuery roomQuery;


    @Test
    public void 예약하기() {
        // given(준비): 어떠한 데이터가 준비되었을 때
        Instant reservationStartDate = Instant.parse("2024-02-03T11:00:00.000Z");
        Instant reservationEndDate = Instant.parse("2024-02-05T11:00:00.000Z");

        ReservationDto.ReserveRequest request = new ReservationDto.ReserveRequest();
        request.setRoomName("그린룸");
        request.setReservationStartDate(reservationStartDate);
        request.setReservationEndDate(reservationEndDate);

        ReservationCommand.ReserveRequest commend = ReservationCommand.ReserveRequest.builder()
                .roomName(request.getRoomName())
                .reservationStartDate(request.getReservationStartDate())
                .reservationEndDate(request.getReservationEndDate())
                .build();

        // when(실행): 어떠한 함수를 실행하면
        boolean result = reservationService.reserve(commend);

        // then(검증): 어떠한 결과가 나와야 한다.
        assertThat(result).isTrue();
    }
}