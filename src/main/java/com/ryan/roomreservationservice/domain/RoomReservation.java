package com.ryan.roomreservationservice.domain;

import com.ryan.roomreservationservice.util.enums.ReservationStatus;
import com.ryan.roomreservationservice.util.enums.ReservationStatusConverter;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.time.LocalTime;

@Entity
@NoArgsConstructor
@Getter
@Table(name = "room_reservation")
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

        return this.reservation.checkAvailabilityPeriod(new DateRange(reservationStartDate, reservationEndDate));
    }

    public boolean isAvailableStatus() {
        return this.reservationStatus.isAvailableStatus();
    }

    public ReservationStatus completeReservation() {
        if (this.reservationStatus == ReservationStatus.PENDING)
            this.reservationStatus = ReservationStatus.CONFIRMED;

        return this.reservationStatus;
    }

    public void changeCheckIn(LocalTime checkInTime) {
        this.checkInOut = TimeRange.changeStart(checkInTime, this.checkInOut);
    }

    public void changeCheckOut(LocalTime checkOutTime) {
        this.checkInOut = TimeRange.changeEnd(this.checkInOut, checkOutTime);
    }

}
