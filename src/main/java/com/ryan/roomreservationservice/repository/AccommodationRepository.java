package com.ryan.roomreservationservice.repository;

import com.ryan.roomreservationservice.persistence.AccommodationJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class AccommodationRepository {
    private final AccommodationJpaRepository accommodationJpaRepository;
}
