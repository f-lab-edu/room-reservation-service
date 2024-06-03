package com.ryan.roomreservationservice.adapter.out.persistence;

import com.ryan.roomreservationservice.adapter.out.persistence.entity.AccommodationEntity;
import com.ryan.roomreservationservice.adapter.out.persistence.entity.AccommodationJpaRepository;
import com.ryan.roomreservationservice.adapter.out.persistence.entity.RoomEntity;
import com.ryan.roomreservationservice.application.port.out.QueryAccommodationPort;
import com.ryan.roomreservationservice.domain.record.LocalDateRange;
import com.ryan.roomreservationservice.utils.exception.ErrorMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class AccommodationPersistenceAdapter implements QueryAccommodationPort {

    private final AccommodationJpaRepository accommodationJpaRepository;

    @Override
    public AccommodationEntity findOneAccommodationsByRoomAndAccommodationPeriod(RoomEntity roomEntity, LocalDateRange accommodationPeriod) {
        return this.accommodationJpaRepository.findOneAccommodationsByRoomAndAccommodationPeriod(roomEntity, accommodationPeriod.start(), accommodationPeriod.end())
                .orElseThrow(() -> new IllegalArgumentException(ErrorMessage.NOT_FOUNT_ACCOMMODATION));
    }
}
