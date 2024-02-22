package com.ryan.roomreservationservice.domain;

import com.ryan.roomreservationservice.util.enums.ErrorType;
import com.ryan.roomreservationservice.util.enums.ReservationStatus;
import com.ryan.roomreservationservice.util.enums.ReservationStatusConverter;
import com.ryan.roomreservationservice.util.enums.StatusCode;
import com.ryan.roomreservationservice.util.exception.CommonException;
import com.ryan.roomreservationservice.util.exception.ErrorMessage;
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
        if (!this.reservationStatus.isAvailableStatus())
            throw CommonException.builder()
                    .errorType(ErrorType.DEVELOPER)
                    .status(StatusCode.FAIL.getStatusCode())
                    .clientErrorMessage(ErrorMessage.NOT_TRANSITION_TO_PENDING)
                    .build();

        this.reservationStatus = ReservationStatus.PENDING;
    }

    public void confirmReservation() {
        if (!this.reservationStatus.isPendingStatus())
            throw CommonException.builder()
                    .errorType(ErrorType.DEVELOPER)
                    .status(StatusCode.FAIL.getStatusCode())
                    .clientErrorMessage(ErrorMessage.NOT_CONFIRM_RESERVATION)
                    .build();

        this.reservationStatus = ReservationStatus.CONFIRMED;
    }

}
