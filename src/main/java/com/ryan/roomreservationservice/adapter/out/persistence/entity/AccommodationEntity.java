package com.ryan.roomreservationservice.adapter.out.persistence.entity;

import com.ryan.roomreservationservice.domain.enums.AccommodationStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "accommodation")
public class AccommodationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long accommodationId;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "roomId")
    private RoomEntity room;
    @Enumerated(EnumType.STRING)
    private AccommodationStatus status;
    private BigDecimal price;
}
