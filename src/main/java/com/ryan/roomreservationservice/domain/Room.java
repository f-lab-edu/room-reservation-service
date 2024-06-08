package com.ryan.roomreservationservice.domain;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.ZoneId;

@Getter
public class Room {
    private Long roomId;
    private ZoneId zoneId;
    private String name;
    private BigDecimal basicPrice;

    @Builder
    public Room(Long roomId, ZoneId zoneId, String name, BigDecimal basicPrice) {
        this.roomId = roomId;
        this.zoneId = zoneId;
        this.name = name;
        this.basicPrice = basicPrice;
    }
}
