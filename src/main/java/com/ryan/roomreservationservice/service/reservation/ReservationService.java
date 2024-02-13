package com.ryan.roomreservationservice.service.reservation;


import java.util.List;

public interface ReservationService {
    List<ReservationInfo.GetReservationList> getReservationList(ReservationCommand.GetReservationListRequest request);
    boolean reserve(ReservationCommand.ReserveRequest request);
}
