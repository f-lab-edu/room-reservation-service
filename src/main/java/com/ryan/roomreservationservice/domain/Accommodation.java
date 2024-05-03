package com.ryan.roomreservationservice.domain;

import com.ryan.roomreservationservice.domain.enums.AccommodationStatus;
import com.ryan.roomreservationservice.utils.exception.ErrorMessage;

import java.util.List;

public class Accommodation {
    private AccommodationStatus status;

    public Accommodation(AccommodationStatus status) {
        this.status = status;
    }

    public List<Accommodation> showSpecificDateAccommodations(DateRange dateRange) {
        return List.of();
    }

    public void confirmReservation(Accommodation accommodation) {
        switch (accommodation.status) {
            case AccommodationStatus.AVAILABLE:
                this.pendingReservation(accommodation);
                break;
            case AccommodationStatus.BLOCK:
            case AccommodationStatus.PENDING:
            case AccommodationStatus.CONFIRMED:
            default:
                throw new IllegalArgumentException(ErrorMessage.UNAVAILABLE_RESERVATION);
        }
    }

    private void pendingReservation(Accommodation accommodation) {
        accommodation.status = AccommodationStatus.PENDING;
    }

    public void completeReservation(Accommodation accommodation) {
        accommodation.status = AccommodationStatus.CONFIRMED;
    }
}
