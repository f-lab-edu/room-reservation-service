package com.ryan.roomreservationservice.adapter.out.persistence;

import com.ryan.roomreservationservice.adapter.out.persistence.entity.ReservationJpaRepository;
import com.ryan.roomreservationservice.adapter.out.persistence.mapper.AccommodationPersistenceMapper;
import com.ryan.roomreservationservice.adapter.out.persistence.mapper.MemberPersistenceMapper;
import com.ryan.roomreservationservice.adapter.out.persistence.mapper.ReservationPersistenceMapper;
import com.ryan.roomreservationservice.application.port.out.CommandReservationPort;
import com.ryan.roomreservationservice.application.port.out.QueryReservationPort;
import com.ryan.roomreservationservice.domain.Accommodation;
import com.ryan.roomreservationservice.domain.Member;
import com.ryan.roomreservationservice.domain.Reservation;
import com.ryan.roomreservationservice.utils.exception.ErrorMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class ReservationPersistenceAdapter implements CommandReservationPort, QueryReservationPort {
    private final ReservationJpaRepository reservationJpaRepository;
    private final ReservationPersistenceMapper reservationPersistenceMapper;
    private final MemberPersistenceMapper memberPersistenceMapper;
    private final AccommodationPersistenceMapper accommodationPersistenceMapper;

    @Override
    public void save(Reservation reservation) {
        var reservationEntity = this.reservationPersistenceMapper.mapToReservationEntity(reservation);
        this.reservationJpaRepository.save(reservationEntity);
    }

    @Override
    public List<Reservation> findByMember(Member member) {
        var memberEntity = this.memberPersistenceMapper.mapToMemberEntity(member);

       return this.reservationJpaRepository.findByMember(memberEntity)
                .stream()
                .map(this.reservationPersistenceMapper::mapToReservation)
               .collect(Collectors.toList());
    }

    @Override
    public Reservation findOneByMemberAndAccommodation(Member member, Accommodation accommodation) {
        var memberEntity = this.memberPersistenceMapper.mapToMemberEntity(member);
        var accommodationEntity = this.accommodationPersistenceMapper.mapToAccommodationEntity(accommodation);

        var reservationEntity = this.reservationJpaRepository.findOneByMemberAndAccommodation(memberEntity, accommodationEntity)
                .orElseThrow(() -> new IllegalArgumentException(ErrorMessage.NOT_FOUND_RESERVATION_BY_MEMBER));

        return this.reservationPersistenceMapper.mapToReservation(reservationEntity);
    }
}
