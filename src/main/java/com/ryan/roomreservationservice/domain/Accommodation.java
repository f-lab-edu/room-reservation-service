package com.ryan.roomreservationservice.domain;

import com.ryan.roomreservationservice.util.enums.AccommodationAvailability;
import com.ryan.roomreservationservice.util.enums.AccommodationAvailabilityConverter;
import com.ryan.roomreservationservice.util.enums.CommonStatusCode;
import com.ryan.roomreservationservice.util.enums.ErrorType;
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
@Table(name = "accommodation")
public class Accommodation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long accommodationId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id")
    private Room room;

    @Convert(converter = AccommodationAvailabilityConverter.class)
    @Column(name = "reservation_status")
    private AccommodationAvailability availability;

    @Column(name = "reservation_date")
    private Instant reservationDate;

    @Builder
    public Accommodation(Room room, AccommodationAvailability availability, Instant reservationDate) {
        this.room = room;
        this.availability = availability;
        this.reservationDate = reservationDate;
    }

    public boolean isAvailableStatus() {
        return this.availability.isAvailableStatus();
    }

    public void transitionToPending() {
        if (!this.availability.isAvailableStatus())
            throw CommonException.builder()
                    .errorType(ErrorType.DEVELOPER)
                    .status(CommonStatusCode.FAIL.getStatusCode())
                    .clientErrorMessage(ErrorMessage.NOT_TRANSITION_TO_PENDING)
                    .build();

        this.availability = AccommodationAvailability.PENDING;
    }

    public void confirmReservation() {
        if (!this.availability.isPendingStatus())
            throw CommonException.builder()
                    .errorType(ErrorType.DEVELOPER)
                    .status(CommonStatusCode.FAIL.getStatusCode())
                    .clientErrorMessage(ErrorMessage.NOT_CONFIRM_RESERVATION)
                    .build();

        this.availability = AccommodationAvailability.CONFIRMED;
    }

}
