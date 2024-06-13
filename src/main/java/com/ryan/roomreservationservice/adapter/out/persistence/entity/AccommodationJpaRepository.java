package com.ryan.roomreservationservice.adapter.out.persistence.entity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.Optional;

public interface AccommodationJpaRepository extends JpaRepository<AccommodationEntity, Long> {

    @Query("select a from AccommodationEntity a left join fetch a.roomEntity r where r = :room and a.accommodationPeriod.start = :start and a.accommodationPeriod.end = :end and a.status = 'AVAILABLE'")
    Optional<AccommodationEntity> findOneAccommodationsByRoomAndAccommodationPeriod(@Param("room") RoomEntity roomEntity, @Param("start") LocalDate start, @Param("end") LocalDate end);
}
