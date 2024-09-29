package com.ryan.roomreservationservice.application.port.out;

import com.ryan.roomreservationservice.domain.Room;

public interface QueryRoomPort {
    Room findOneByName(String name);
}
