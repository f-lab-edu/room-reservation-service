package com.ryan.roomreservationservice.persistence;

import com.ryan.roomreservationservice.domain.RoomReservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.Instant;
import java.util.List;

public interface RoomReservationJpaRepository extends JpaRepository<RoomReservation, Long> {
    @Query("select rr from RoomReservation  rr left join fetch rr.room where rr.reservation.start >= :start and rr.reservation.end <= :end")
    List<RoomReservation> findAllByReservation(@Param("start") Instant start, @Param("end") Instant end);
}
