package com.ryan.roomreservationservice.domain;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class Member {
    private Long memberId;
    private String name;
    private List<PaymentHistory> paymentHistories;
    private List<Card> cards;

    @Builder
    public Member(Long memberId, String name, List<PaymentHistory> paymentHistories, List<Card> cards) {
        this.memberId = memberId;
        this.name = name;
        this.paymentHistories = paymentHistories;
        this.cards = cards;
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
