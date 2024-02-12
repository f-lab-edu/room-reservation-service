package com.ryan.roomreservationservice.service.reservation;

import com.ryan.roomreservationservice.domain.DateRange;
import com.ryan.roomreservationservice.service.room.RoomQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RoomReservationServiceImp implements ReservationService {

    private final ReservationQuery reservationQuery;
    private final ReservationStore reservationStore;
    private final RoomQuery roomQuery;

    @Override
    public List<ReservationInfo.GetReservationList> getReservationList(ReservationCommand.GetReservationListRequest command) {
        return reservationQuery.findAllByReservation(command.getStart(), command.getEnd()).stream()
                .map(ReservationInfo.GetReservationList::new)
                .collect(Collectors.toList());
    }

    @Override
    public boolean reserve(ReservationCommand.ReserveRequest command) {
        var room = this.roomQuery.findOneByRoomName(command.getRoomName());
        var dateRange = new DateRange(command.getReservationStartDate(), command.getReservationEndDate());
        this.reservationStore.reserve(room, dateRange);

        return true;
    }
}
