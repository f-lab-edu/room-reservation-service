package com.ryan.roomreservationservice.domain;

import com.ryan.roomreservationservice.util.enums.ReservationStatus;
import com.ryan.roomreservationservice.util.enums.ReservationStatusConverter;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "slot")
public class ReservationSlot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long slotId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id")
    private Room room;

    @Convert(converter = ReservationStatusConverter.class)
    @Column(name = "reservation_status")
    private ReservationStatus reservationStatus;

    @Column(name = "reservation_date")
    private Instant reservationDate;

    @Builder
    public ReservationSlot(Room room, ReservationStatus reservationStatus, Instant reservationDate) {
        this.room = room;
        this.reservationStatus = reservationStatus;
        this.reservationDate = reservationDate;
    }

    public boolean isAvailableStatus() {
        return this.reservationStatus.isAvailableStatus();
    }

    public void transitionToPending() {
        this.reservationStatus = ReservationStatus.PENDING;
    }

    public void confirmReservation() {
        this.reservationStatus = ReservationStatus.CONFIRMED;
    }

}
