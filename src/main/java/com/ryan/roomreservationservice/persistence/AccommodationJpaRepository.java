package com.ryan.roomreservationservice.persistence;

import com.ryan.roomreservationservice.entity.AccommodationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccommodationJpaRepository extends JpaRepository<AccommodationEntity, Long> {
}
