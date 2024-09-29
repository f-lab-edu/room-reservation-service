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
                .member(this.memberPersistenceMapper.mapToMemberEntity(reservation.getMember()))
                .accommodation(this.accommodationPersistenceMapper.mapToAccommodationEntity(reservation.getAccommodation()))
                .reservationDate(reservation.getReservationDate())
                .build();
    }

    public Reservation mapToReservation(ReservationEntity reservationEntity) {
        return Reservation.builder()
                .reservationId(reservationEntity.getReservationId())
                .member(this.memberPersistenceMapper.mapToMember(reservationEntity.getMember()))
                .reservationDate(reservationEntity.getReservationDate())
                .accommodation(this.accommodationPersistenceMapper.mapToAccommodation(reservationEntity.getAccommodation()))
                .build();
    }

}
