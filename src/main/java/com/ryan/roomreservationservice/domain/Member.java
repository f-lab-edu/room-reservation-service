package com.ryan.roomreservationservice.domain;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class Member {
    private Long memberId;
    private String name;
    private List<Reservation> reservations;
    private List<PaymentHistory> paymentHistories;
    private List<Card> cards;

    @Builder
    public Member(String name, List<Reservation> reservations, List<PaymentHistory> paymentHistories, List<Card> cards) {
        this.name = name;
        this.reservations = reservations;
        this.paymentHistories = paymentHistories;
        this.cards = cards;
    }

    @Builder
    public Member(Long memberId, String name, List<Reservation> reservations, List<PaymentHistory> paymentHistories, List<Card> cards) {
        this.memberId = memberId;
        this.name = name;
        this.reservations = reservations;
        this.paymentHistories = paymentHistories;
        this.cards = cards;
    }

    public void registerReservation(Reservation reservation) {
        this.reservations.add(reservation);
    }

    public List<Reservation> confirmReservation() {
        return this.reservations;
    }

    public List<PaymentHistory> confirmPaymentHistories() {
        return this.paymentHistories;
    }

    public void addCard(Card card) {
        this.cards.add(card);
    }

    public void removeCard(Card card) {
       this.cards.removeIf((memberCard) -> memberCard.equals(card));
    }
}
