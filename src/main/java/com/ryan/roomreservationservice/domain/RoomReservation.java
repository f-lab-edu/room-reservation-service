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
@Table(name = "room_reservation")
@NoArgsConstructor
public class RoomReservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "room_reservation_id")
    private Long roomReservationId;

    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room room;

    @Convert(converter = ReservationStatusConverter.class)
    @Column(name = "reservation_status")
    private ReservationStatus reservationStatus;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "start", column = @Column(name = "reservation_start_date")),
            @AttributeOverride(name = "end", column = @Column(name = "reservation_end_date"))
    })
    private DateRange reservation;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "start", column = @Column(name = "check_in_date")),
            @AttributeOverride(name = "end", column = @Column(name = "check_out_date"))
    })
    private TimeRange checkInOut;

    @Builder
    public RoomReservation(Room room, ReservationStatus reservationStatus, DateRange reservation) {
        this.room = room;
        this.reservationStatus = reservationStatus;
        this.reservation = reservation;
    }

    public boolean checkAvailabilityDateStatus(Instant reservationStartDate, Instant reservationEndDate) {
        if (Instant.now().isAfter(reservationStartDate)) return false;

        return this.reservation.checkAvailabilityPeriod(
                DateRange.builder()
                        .start(reservationStartDate)
                        .end(reservationEndDate)
                        .build()
        );
    }
}
