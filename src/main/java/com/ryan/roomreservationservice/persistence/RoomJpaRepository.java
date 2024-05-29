package com.ryan.roomreservationservice.persistence;

import com.ryan.roomreservationservice.entity.RoomEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomJpaRepository extends JpaRepository<RoomEntity, Long> {
}
