package com.ryan.roomreservationservice.application.port.in;

import com.ryan.roomreservationservice.application.port.in.command.ReservationCommand;

public interface ReserveUseCase {
    void reserve(ReservationCommand.ReserveCommand command);
}
