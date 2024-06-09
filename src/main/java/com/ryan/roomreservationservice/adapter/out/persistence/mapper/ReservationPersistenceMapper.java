package com.ryan.roomreservationservice.adapter.out.persistence.mapper;

import com.ryan.roomreservationservice.adapter.out.persistence.entity.ReservationEntity;
import com.ryan.roomreservationservice.domain.Reservation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ReservationPersistenceMapper {
    private final AccommodationPersistenceMapper accommodationPersistenceMapper;
    private final MemberPersistenceMapper memberPersistenceMapper;

    public ReservationEntity mapToReservationEntity(Reservation reservation) {
        return ReservationEntity.builder()
                .reservationId(reservation.getReservationId())
                .memberEntity(this.memberPersistenceMapper.mapToMemberEntity(reservation.getMember()))
                .accommodationEntity(this.accommodationPersistenceMapper.mapToAccommodationEntity(reservation.getAccommodation()))
                .reservationDate(reservation.getReservationDate())
                .build();
    }

}
