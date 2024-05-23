package com.ryan.roomreservationservice.domain;

import com.ryan.roomreservationservice.domain.enums.AccommodationStatus;
import com.ryan.roomreservationservice.utils.exception.ErrorMessage;
import lombok.Getter;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

@Getter
public class Accommodation {
    private Room room;
    private AccommodationStatus status;
    private BigDecimal price;

    public Accommodation(Room room, AccommodationStatus status, BigDecimal price) {
        this.room = room;
        this.status = status;
        this.price = price;
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

    public void changeToCompletionStatus(Accommodation accommodation) {
        accommodation.status = AccommodationStatus.COMPLETED;
    }

    public BigDecimal calculateRoomPaymentAmount(LocalDateRange reservationDate) {
        BigDecimal reservationPeriod = BigDecimal.valueOf(reservationDate.calculateDayPeriod());
        return this.price.multiply(reservationPeriod);
    }

    public BigDecimal calculateRoomRefundAmount(LocalDate cancelLocalDate, LocalDateRange reservationDate) {
        LocalDate start = reservationDate.start();

        if (start.isBefore(cancelLocalDate))
            throw new IllegalArgumentException(ErrorMessage.CANCEL_REQUEST_DATE_MUST_BE_BEFORE_CHECK_IN);

        long beforeDay = reservationDate.calculatePeriodBeforeStartDate(cancelLocalDate);
        if (3 < beforeDay && beforeDay < 7) {
            BigDecimal refundRate = BigDecimal.valueOf(70).divide(BigDecimal.valueOf(100));
            BigDecimal refundAmount = this.price.multiply(refundRate);

            return refundAmount.setScale(0, RoundingMode.HALF_UP);
        }

        return this.price;
    }

    private void pendingReservation(Accommodation accommodation) {
        accommodation.status = AccommodationStatus.PENDING;
    }
}
