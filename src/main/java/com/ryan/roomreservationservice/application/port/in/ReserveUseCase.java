package com.ryan.roomreservationservice.application.port.in;

import com.ryan.roomreservationservice.application.port.in.command.ReservationCommand;
import com.ryan.roomreservationservice.application.port.in.query.ReservationQuery;

import java.util.List;

public interface ReserveUseCase {
    void reserve(ReservationCommand.ReserveCommand command);
    List<ReservationQuery.Main> getReservations(ReservationCommand.GetReservationsByMemberCommand command);
    boolean confirmAccommodationReservationByMember(ReservationCommand.ConfirmAccommodationReservationByMemberCommand command);
}
