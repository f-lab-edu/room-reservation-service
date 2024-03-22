package com.ryan.roomreservationservice.persistence;

import com.ryan.roomreservationservice.domain.Accommodation;
import com.ryan.roomreservationservice.domain.Room;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.Instant;
import java.util.List;

public interface AccommodationJpaRepository extends JpaRepository<Accommodation, Long> {
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("select a from Accommodation a where a.room = :room and a.reservationDate >= :start and a.reservationDate <= :end")
    List<Accommodation> findByRoomAndReservationDateWithPessimisticLock(@Param("room") Room room, @Param("start") Instant start, @Param("end") Instant end);
}
