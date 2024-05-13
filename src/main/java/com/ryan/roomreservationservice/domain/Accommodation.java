package com.ryan.roomreservationservice.domain;

import com.ryan.roomreservationservice.domain.enums.AccommodationStatus;
import com.ryan.roomreservationservice.utils.exception.ErrorMessage;
import lombok.Getter;

import java.util.List;

@Getter
public class Accommodation {
    private Room room;
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

    public int getPaymentAmount(DateRange dateRange) {
       return this.room.calculateRoomPayment(dateRange);
    }
}
