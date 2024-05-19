package com.ryan.roomreservationservice.domain;

import com.ryan.roomreservationservice.domain.enums.AccommodationStatus;
import com.ryan.roomreservationservice.utils.exception.ErrorMessage;
import lombok.Getter;

import java.util.List;

@Getter
public class Accommodation {
    private Room room;
    private AccommodationStatus status;

    public Accommodation(Room room, AccommodationStatus status) {
        this.room = room;
        this.status = status;
    }

    public List<Accommodation> showSpecificDateAccommodations(LocalDateRange localDateRange) {
        return List.of();
    }

    public void confirmReservation(Accommodation accommodation) {
        switch (accommodation.status) {
            case AccommodationStatus.AVAILABLE:
                this.pendingReservation(accommodation);
                break;
            case AccommodationStatus.BLOCK:
            case AccommodationStatus.PENDING:
            case AccommodationStatus.COMPLETED:
            default:
                throw new IllegalArgumentException(ErrorMessage.UNAVAILABLE_RESERVATION);
        }
    }

    private void pendingReservation(Accommodation accommodation) {
        accommodation.status = AccommodationStatus.PENDING;
    }

    public void changeToCompletionStatus(Accommodation accommodation) {
        accommodation.status = AccommodationStatus.COMPLETED;
    }

    public long getPaymentAmount(LocalDateRange localDateRange) {
       return this.room.calculateRoomPayment(localDateRange);
    }
}
