package com.ryan.roomreservationservice.application.service;

import com.ryan.roomreservationservice.application.port.in.ReserveUseCase;
import com.ryan.roomreservationservice.application.port.in.command.ReservationCommand;
import com.ryan.roomreservationservice.application.port.out.CommandReservationPort;
import com.ryan.roomreservationservice.application.port.out.QueryAccommodationPort;
import com.ryan.roomreservationservice.application.port.out.QueryMemberPort;
import com.ryan.roomreservationservice.domain.Home;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReservationService implements ReserveUseCase {
    private final QueryMemberPort queryMemberPort;
    private final QueryAccommodationPort queryAccommodationPort;
    private final CommandReservationPort commandReservationPort;

    @Override
    public void reserve(ReservationCommand.ReserveCommand command) {
        var memberName = command.getMemberName();
        var roomName = command.getRoomName();
        var reservationDate = command.getReservationDate();

        var member = this.queryMemberPort.findOneByName(memberName);
        var accommodation = this.queryAccommodationPort.findOneAccommodationsByRoomNameAndAccommodationPeriod(roomName, reservationDate);

        var home = new Home();
        var reservation = home.reserve(member, reservationDate, accommodation);

        this.commandReservationPort.save(reservation);
    }
}
