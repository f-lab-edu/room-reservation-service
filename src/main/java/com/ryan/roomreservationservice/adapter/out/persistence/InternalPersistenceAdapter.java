package com.ryan.roomreservationservice.adapter.out.persistence;

import com.ryan.roomreservationservice.adapter.out.persistence.entity.RoomEntity;
import com.ryan.roomreservationservice.adapter.out.persistence.entity.RoomJpaRepository;
import com.ryan.roomreservationservice.utils.exception.ErrorMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
class InternalPersistenceAdapter {
    private final RoomJpaRepository roomJpaRepository;

    public RoomEntity findOneRoomByName(String name) {
        return this.roomJpaRepository.findByName(name)
                .orElseThrow(() -> new IllegalArgumentException(ErrorMessage.Not_FOUNT_ROOM));
    }
}
