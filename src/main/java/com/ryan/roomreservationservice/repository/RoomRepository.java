package com.ryan.roomreservationservice.repository;

import com.ryan.roomreservationservice.persistence.RoomJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class RoomRepository {
    private final RoomJpaRepository roomJpaRepository;
}
