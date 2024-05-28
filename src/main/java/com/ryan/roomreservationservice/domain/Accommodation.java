package com.ryan.roomreservationservice.domain;

import com.ryan.roomreservationservice.domain.enums.AccommodationStatus;
import com.ryan.roomreservationservice.domain.record.LocalDateRange;
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
    private static final BigDecimal SEVENTY_PERCENT = BigDecimal.valueOf(70).divide(BigDecimal.valueOf(100));

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
        reservationDate.assertDateBeforeTheStart(cancelLocalDate);

        long beforeDay = reservationDate.calculatePeriodBeforeStartDate(cancelLocalDate);
        if (3 < beforeDay && beforeDay < 7) {
            return this.price.multiply(SEVENTY_PERCENT).setScale(0, RoundingMode.HALF_UP);
        }
        return this.price;
    }

    private void pendingReservation(Accommodation accommodation) {
        accommodation.status = AccommodationStatus.PENDING;
    }
}
