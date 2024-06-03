package com.ryan.roomreservationservice.adapter.out.persistence.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "room")
public class RoomEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long roomId;
    private String zoneId;
    private String name;
    private BigDecimal basicPrice;

    @Builder
    public RoomEntity(String zoneId, String name, BigDecimal basicPrice) {
        this.zoneId = zoneId;
        this.name = name;
        this.basicPrice = basicPrice;
    }
}
