package com.ryan.roomreservationservice.service.accommodation;

import com.ryan.roomreservationservice.domain.Accommodation;
import com.ryan.roomreservationservice.domain.DateRange;
import com.ryan.roomreservationservice.domain.Room;

import java.util.List;

public interface AccommodationQuery {
   List<Accommodation> findByRoomAndReservationDateWithPessimisticLock(Room room, DateRange dateRange);
}
