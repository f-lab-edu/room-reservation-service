package com.ryan.roomreservationservice.domain;

import java.time.ZoneId;
import java.util.List;

public class Room {
    private ZoneId zoneId;
    private String name;
    private long price;

    public Room(ZoneId zoneId, String name, long price) {
        this.zoneId = zoneId;
        this.name = name;
        this.price = price;
    }

    public List<Room> showRoomInfos() {
        return List.of();
    }

    public long calculateRoomPayment(DateRange dateRange) {
        long reservationPeriod = dateRange.calculateDayPeriod();
        return this.price * reservationPeriod;
    }

}
