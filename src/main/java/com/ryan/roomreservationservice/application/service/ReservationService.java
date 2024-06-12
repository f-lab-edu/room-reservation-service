package com.ryan.roomreservationservice.application.service;

import com.ryan.roomreservationservice.application.port.in.ReserveUseCase;
import com.ryan.roomreservationservice.application.port.in.command.ReservationCommand;
import com.ryan.roomreservationservice.application.port.in.query.ReservationQuery;
import com.ryan.roomreservationservice.application.port.out.CommandReservationPort;
import com.ryan.roomreservationservice.application.port.out.QueryAccommodationPort;
import com.ryan.roomreservationservice.application.port.out.QueryMemberPort;
import com.ryan.roomreservationservice.application.port.out.QueryReservationPort;
import com.ryan.roomreservationservice.application.service.mapper.ReservationServiceMapper;
import com.ryan.roomreservationservice.domain.Home;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReservationService implements ReserveUseCase {
    private final ReservationServiceMapper mapper;
    private final QueryMemberPort queryMemberPort;
    private final QueryAccommodationPort queryAccommodationPort;
    private final CommandReservationPort commandReservationPort;
    private final QueryReservationPort queryReservationPort;

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

    @Override
    public List<ReservationQuery.Main> getReservations(ReservationCommand.GetReservationsByMemberCommand command) {
        var memberName = command.getMemberName();

        var member = this.queryMemberPort.findOneByName(memberName);
        var reservations = this.queryReservationPort.getReservationsByMember(member);

        return reservations.stream().map(this.mapper::mapToMain)
                .collect(Collectors.toList());
    }
}
