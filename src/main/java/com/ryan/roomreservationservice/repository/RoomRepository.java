package com.ryan.roomreservationservice.repository;

import com.ryan.roomreservationservice.domain.Room;
import com.ryan.roomreservationservice.persistance.RoomJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class RoomRepository {

    private final RoomJpaRepository roomJpaRepository;

    public List<Room> findAll() {
        return this.roomJpaRepository.findAll();
    }
}
