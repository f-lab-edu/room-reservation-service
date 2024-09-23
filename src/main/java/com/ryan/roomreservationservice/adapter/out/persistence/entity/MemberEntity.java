package com.ryan.roomreservationservice.adapter.out.persistence.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "member")
public class MemberEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberId;
    private String userId;
    private String password;
    private String name;

    @Builder
    public MemberEntity(Long memberId, String userId, String password, String name) {
        this.memberId = memberId;
        this.userId = userId;
        this.password = password;
        this.name = name;
    }
}
