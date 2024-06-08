package com.ryan.roomreservationservice.application.port.out;

import com.ryan.roomreservationservice.domain.Accommodation;
import com.ryan.roomreservationservice.domain.record.LocalDateRange;

public interface QueryAccommodationPort {

    Accommodation findOneAccommodationsByRoomNameAndAccommodationPeriod(String roomName, LocalDateRange accommodationPeriod);
}
