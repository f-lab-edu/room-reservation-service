package com.ryan.roomreservationservice.application.service;

import com.ryan.roomreservationservice.application.port.in.command.ReservationCommand;
import com.ryan.roomreservationservice.application.port.out.CommandReservationPort;
import com.ryan.roomreservationservice.application.port.out.QueryAccommodationPort;
import com.ryan.roomreservationservice.application.port.out.QueryMemberPort;
import com.ryan.roomreservationservice.domain.Accommodation;
import com.ryan.roomreservationservice.domain.Member;
import com.ryan.roomreservationservice.domain.Room;
import com.ryan.roomreservationservice.domain.enums.AccommodationStatus;
import com.ryan.roomreservationservice.domain.record.LocalDateRange;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.ZoneId;
import java.util.ArrayList;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ReservationServiceTest {

    @InjectMocks
    private ReservationService reservationService;

    @Mock
    private QueryMemberPort queryMemberPort;

    @Mock
    private QueryAccommodationPort queryAccommodationPort;

    @Mock
    private CommandReservationPort commandReservationPort;

    @Test
    public void 예약하기_검증() {
        // given(준비): 어떠한 데이터가 준비되었을 때
        String roomName = "그린룸";

        ReservationCommand.ReserveCommand command = ReservationCommand.ReserveCommand
                .builder()
                .memberName("Ryan")
                .roomName(roomName)
                .reservationDate(LocalDateRange.parse("2024-06-07", "2024-06-08"))
                .build();

        Member member = Member.builder()
                .name(command.getMemberName())
                .reservations(new ArrayList<>())
                .paymentHistories(new ArrayList<>())
                .cards(new ArrayList<>())
                .build();

        Room room = Room.builder()
                .roomId(1L)
                .zoneId(ZoneId.of("Asia/Seoul"))
                .name(roomName)
                .basicPrice(BigDecimal.valueOf(300000))
                .build();

        LocalDateRange reservationDate = LocalDateRange.parse("2024-06-07", "2024-06-08");
        Accommodation accommodation = Accommodation.builder()
                .accommodationId(1L)
                .room(room)
                .status(AccommodationStatus.AVAILABLE)
                .price(BigDecimal.valueOf(300000))
                .accommodationPeriod(reservationDate)
                .build();

        when(this.queryMemberPort.findOneByName(command.getMemberName())).thenReturn(member);
        when(this.queryAccommodationPort.findOneAccommodationsByRoomNameAndAccommodationPeriod(command.getRoomName(), command.getReservationDate())).thenReturn(accommodation);

        // when(실행): 어떠한 함수를 실행하면
        this.reservationService.reserve(command);

        // then(검증): 어떠한 결과가 나와야 한다.
        verify(this.queryMemberPort, times(1)).findOneByName(command.getMemberName());
        verify(this.queryAccommodationPort, times(1)).findOneAccommodationsByRoomNameAndAccommodationPeriod(command.getRoomName(), command.getReservationDate());
        verify(this.commandReservationPort, times(1)).save(any());
    }
}