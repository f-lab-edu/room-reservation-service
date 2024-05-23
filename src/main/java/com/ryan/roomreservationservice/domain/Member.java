package com.ryan.roomreservationservice.domain;

import lombok.Getter;

import java.util.List;

@Getter
public class Member {
    private String name;
    private List<Reservation> reservations;
    private List<PaymentHistory> paymentHistories;
    private List<Card> cards;

    public Member(String name, List<Reservation> reservations, List<PaymentHistory> paymentHistories) {
        this.name = name;
        this.reservations = reservations;
        this.paymentHistories = paymentHistories;
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
