package com.ryan.roomreservationservice.adapter.out.persistence.entity;

import com.ryan.roomreservationservice.domain.LocalDateRange;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "reservation")
public class ReservationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reservationId;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "memberId")
    private MemberEntity memberEntity;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "accommodationId")
    private AccommodationEntity accommodationEntity;
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "start", column = @Column(name = "reservation_start_date")),
            @AttributeOverride(name = "end", column = @Column(name = "reservation_end_date"))
    })
    private LocalDateRange reservationDate;
}
