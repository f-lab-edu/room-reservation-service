package com.ryan.roomreservationservice.service.reservation;

import com.ryan.roomreservationservice.domain.DateRange;
import com.ryan.roomreservationservice.domain.Room;

public interface ReservationStore {
    void reserve(Room room, DateRange dateRange);
}
