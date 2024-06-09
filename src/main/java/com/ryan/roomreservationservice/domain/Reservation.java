package com.ryan.roomreservationservice.domain;

import com.ryan.roomreservationservice.domain.record.LocalDateRange;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
public class Reservation {
    private Long reservationId;
    private Member member;
    private LocalDateRange reservationDate;
    private Accommodation accommodation;

    @Builder
    public Reservation(Long reservationId, Member member, LocalDateRange reservationDate, Accommodation accommodation) {
        this.reservationId = reservationId;
        this.member = member;
        this.reservationDate = reservationDate;
        this.accommodation = accommodation;
    }

    public BigDecimal getReservationAmount() {
        return this.accommodation.calculateRoomPaymentAmount(this.reservationDate);
    }

    public void completeReservation() {
        this.accommodation.changeToCompletionStatus(this.accommodation);
    }

    public BigDecimal getReservationRefundAmount(LocalDate cancelLocalDate) {
        return this.accommodation.calculateRoomRefundAmount(cancelLocalDate, this.reservationDate);
    }

}
