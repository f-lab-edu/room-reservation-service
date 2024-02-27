package com.ryan.roomreservationservice.repository.reservation;

import com.ryan.roomreservationservice.domain.DateRange;
import com.ryan.roomreservationservice.domain.Room;
import com.ryan.roomreservationservice.domain.RoomReservation;
import com.ryan.roomreservationservice.persistence.RoomReservationJpaRepository;
import com.ryan.roomreservationservice.service.reservation.ReservationStore;
import com.ryan.roomreservationservice.util.enums.ReservationStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ReservationStoreImp implements ReservationStore {
    private final RoomReservationJpaRepository roomReservationJpaRepository;

    @Override
    public void reserve(Room room, DateRange dateRange) {
        RoomReservation roomReservation = RoomReservation.builder()
                .room(room)
                .reservationStatus(ReservationStatus.CONFIRMED)
                .reservation(dateRange)
                .build();

        this.roomReservationJpaRepository.save(roomReservation);
    }
}
