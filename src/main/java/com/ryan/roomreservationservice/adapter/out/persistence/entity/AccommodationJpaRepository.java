package com.ryan.roomreservationservice.adapter.out.persistence.entity;

import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.Optional;

public interface AccommodationJpaRepository extends JpaRepository<AccommodationEntity, Long> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("select a from AccommodationEntity a left join fetch a.room r where r = :room and a.accommodationPeriod.start = :start and a.accommodationPeriod.end = :end and a.status = 'AVAILABLE'")
    Optional<AccommodationEntity> findOneByRoomAndAccommodationPeriodWithPessimisticLock(@Param("room") RoomEntity room, @Param("start") LocalDate start, @Param("end") LocalDate end);

    @Query("select a from AccommodationEntity a where a.room = :room and a.accommodationPeriod.start = :start and a.accommodationPeriod.end = :end and a.status = 'AVAILABLE'")
    Optional<AccommodationEntity> findOneByRoomAndAccommodationPeriod(@Param("room") RoomEntity room, @Param("start") LocalDate start, @Param("end") LocalDate end);
}
