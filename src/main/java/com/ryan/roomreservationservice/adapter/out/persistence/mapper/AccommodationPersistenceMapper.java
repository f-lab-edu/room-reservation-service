package com.ryan.roomreservationservice.adapter.out.persistence.mapper;

import com.ryan.roomreservationservice.adapter.out.persistence.entity.AccommodationEntity;
import com.ryan.roomreservationservice.domain.Accommodation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AccommodationPersistenceMapper {
    private final RoomPersistenceMapper roomPersistenceMapper;

    public Accommodation mapToAccommodation(AccommodationEntity accommodationEntity) {
        return Accommodation.builder()
                .room(this.roomPersistenceMapper.mapToRoom(accommodationEntity.getRoomEntity()))
                .price(accommodationEntity.getPrice())
                .build();
    }

    public AccommodationEntity mapToAccommodationEntity(Accommodation accommodation) {
        return AccommodationEntity.builder()
                .accommodationId(accommodation.getAccommodationId())
                .roomEntity(this.roomPersistenceMapper.mapToRoomEntity(accommodation.getRoom()))
                .status(accommodation.getStatus())
                .accommodationPeriod(accommodation.getAccommodationPeriod())
                .build();
    }

}
