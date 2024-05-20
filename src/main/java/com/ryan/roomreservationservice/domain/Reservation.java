package com.ryan.roomreservationservice.domain;

import lombok.Getter;

import java.time.LocalDate;

@Getter
public class Reservation {
    private Long reservationId;
    private Member member;
    private LocalDateRange reservationDate;
    private Accommodation accommodation;

    public Reservation(Member member, LocalDateRange reservationDate, Accommodation accommodation) {
        this.member = member;
        this.reservationDate = reservationDate;
        this.accommodation = accommodation;
    }

    public long getReservationAmount() {
        return this.accommodation.getPaymentAmount(this.reservationDate);
    }

    public void completeReservation() {
        this.accommodation.changeToCompletionStatus(this.accommodation);
    }

    public long getReservationRefundAmount(LocalDate cancelLocalDate) {
        return this.accommodation.getRefundAmount(cancelLocalDate, this.reservationDate);
    }

}
