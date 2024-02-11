package com.ryan.roomreservationservice.domain;

import com.ryan.roomreservationservice.util.enums.ReservationStatus;
import com.ryan.roomreservationservice.util.enums.ReservationStatusConverter;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.Tolerate;

import java.time.Instant;

@Entity
@Builder
@Getter
@Table(name = "room_reservation")
public class RoomReservation {

    @Tolerate
    public RoomReservation() {
    }

    // 고유 키
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "room_reservation_id")
    private Long roomReservationId;

    // 객실 고유 키
    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room room;

    // 예약 상태
    @Convert(converter = ReservationStatusConverter.class)
    @Column(name = "reservation_status")
    private ReservationStatus reservationStatus;

    // 예약날짜 정보
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "start", column = @Column(name = "reservation_start_date")),
            @AttributeOverride(name = "end", column = @Column(name = "reservation_end_date"))
    })
    private DateRange reservation;

    // 입/퇴실 정보
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "start", column = @Column(name = "check_in_date")),
            @AttributeOverride(name = "end", column = @Column(name = "check_out_date"))
    })
    private TimeRange checkInOut;

    public boolean checkAvailabilityDateStatus(Instant reservationStartDate, Instant reservationEndDate) {
        if (Instant.now().isAfter(reservationStartDate)) return false;

        return this.reservation.checkAvailabilityPeriod(new DateRange(reservationStartDate, reservationEndDate));
    }

    public boolean isAvailableStatus() {
        return this.reservationStatus.isAvailableStatus();
    }

}
