package com.ryan.roomreservationservice.persistence;

import com.ryan.roomreservationservice.entity.ReservationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationJpaRepository extends JpaRepository<ReservationEntity, Long> {
}
