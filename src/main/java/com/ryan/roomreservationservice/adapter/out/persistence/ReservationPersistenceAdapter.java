package com.ryan.roomreservationservice.adapter.out.persistence;

import com.ryan.roomreservationservice.adapter.out.persistence.entity.ReservationJpaRepository;
import com.ryan.roomreservationservice.adapter.out.persistence.mapper.ReservationPersistenceMapper;
import com.ryan.roomreservationservice.application.port.out.CommandReservationPort;
import com.ryan.roomreservationservice.application.port.out.QueryReservationPort;
import com.ryan.roomreservationservice.domain.Member;
import com.ryan.roomreservationservice.domain.Reservation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class ReservationPersistenceAdapter implements CommandReservationPort, QueryReservationPort {
    private final ReservationJpaRepository reservationJpaRepository;
    private final ReservationPersistenceMapper mapper;

    @Override
    public void save(Reservation reservation) {
        var reservationEntity = this.mapper.mapToReservationEntity(reservation);
        this.reservationJpaRepository.save(reservationEntity);
    }

    @Override
    public List<Reservation> getReservationsByMember(Member member) {
       return this.reservationJpaRepository.findByMember(member)
                .stream()
                .map(this.mapper::mapToReservation)
               .collect(Collectors.toList());
    }
}
