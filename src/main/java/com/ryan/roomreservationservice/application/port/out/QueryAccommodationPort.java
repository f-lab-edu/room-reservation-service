package com.ryan.roomreservationservice.application.port.out;

import com.ryan.roomreservationservice.domain.Accommodation;
import com.ryan.roomreservationservice.domain.Room;
import com.ryan.roomreservationservice.domain.record.LocalDateRange;

public interface QueryAccommodationPort {

    Accommodation findOneByRoomAndAccommodationPeriodWithPessimisticLock(Room room, LocalDateRange accommodationPeriod);
    Accommodation findOneByRoomAndAccommodationPeriod(Room room, LocalDateRange accommodationPeriod);
}
