package com.ryan.roomreservationservice.adapter.out.persistence.entity;

import com.ryan.roomreservationservice.domain.enums.AccommodationStatus;
import com.ryan.roomreservationservice.domain.record.LocalDateRange;
import jakarta.persistence.*;
import lombok.Builder;
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

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "start", column = @Column(name = "accommodation_start_date")),
            @AttributeOverride(name = "end", column = @Column(name = "accommodation_end_date"))
    })
    private LocalDateRange accommodationPeriod;

    @Builder
    public AccommodationEntity(RoomEntity room, AccommodationStatus status, BigDecimal price, LocalDateRange accommodationPeriod) {
        this.room = room;
        this.status = status;
        this.price = price;
        this.accommodationPeriod = accommodationPeriod;
    }
}
