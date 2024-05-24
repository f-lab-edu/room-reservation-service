package com.ryan.roomreservationservice.domain;

import java.math.BigDecimal;
import java.time.ZoneId;

public class Room {
    private ZoneId zoneId;
    private String name;
    private BigDecimal basicPrice;

    public Room(ZoneId zoneId, String name, BigDecimal basicPrice) {
        this.zoneId = zoneId;
        this.name = name;
        this.basicPrice = basicPrice;
    }
}
