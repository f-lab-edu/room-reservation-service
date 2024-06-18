package com.ryan.roomreservationservice.adapter.out.persistence.entity;


import com.ryan.roomreservationservice.domain.enums.Status;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "card")
public class CardEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cardId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "memberId")
    private MemberEntity member;

    @Enumerated(EnumType.STRING)
    private Status status;
    private String pgType;
    private String paymentKey;
    private String cardName;
    private String cardNumber;
    private String ownerType;
    private Instant createdAt;
}
