package com.ryan.roomreservationservice.application.service;

import com.ryan.roomreservationservice.application.port.in.ReserveUseCase;
import com.ryan.roomreservationservice.application.port.in.command.ReservationCommand;
import com.ryan.roomreservationservice.application.port.in.query.ReservationQuery;
import com.ryan.roomreservationservice.application.port.out.*;
import com.ryan.roomreservationservice.application.service.mapper.ReservationServiceMapper;
import com.ryan.roomreservationservice.application.service.validator.MemberValidator;
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
    private final QueryRoomPort queryRoomPort;
    private final MemberValidator memberValidator;

    @Override
    public void reserve(ReservationCommand.ReserveCommand command) {
        var userId = command.getUserId();
        var roomName = command.getRoomName();
        var reservationDate = command.getReservationDate();

        var findMember = this.queryMemberPort.findOneByUserId(userId);
        var member = this.memberValidator.assertMemberNotExist(findMember);
        var room = this.queryRoomPort.findOneByName(roomName);
        var accommodation = this.queryAccommodationPort.findOneByRoomAndAccommodationPeriodWithPessimisticLock(room, reservationDate);

        var home = new Home();
        var reservation = home.reserve(member, reservationDate, accommodation);

        this.commandReservationPort.save(reservation);
    }

    @Override
    public List<ReservationQuery.Main> getReservations(ReservationCommand.GetReservationsByMemberCommand command) {
        var userId = command.getUserId();

        var findMember = this.queryMemberPort.findOneByUserId(userId);
        var member = this.memberValidator.assertMemberNotExist(findMember);
        var reservations = this.queryReservationPort.findByMember(member);

        return reservations.stream().map(this.mapper::mapToMain)
                .collect(Collectors.toList());
    }

    @Override
    public boolean confirmAccommodationReservationByMember(ReservationCommand.ConfirmAccommodationReservationByMemberCommand command) {
        var userId = command.getUserId();
        var roomName = command.getRoomName();
        var reservationDate = command.getReservationDate();

        var findMember = this.queryMemberPort.findOneByUserId(userId);
        var member = this.memberValidator.assertMemberNotExist(findMember);
        var room = this.queryRoomPort.findOneByName(roomName);
        var accommodation = this.queryAccommodationPort.findOneByRoomAndAccommodationPeriod(room, reservationDate);

        this.queryReservationPort.findOneByMemberAndAccommodation(member, accommodation);
        return true;
    }
}
