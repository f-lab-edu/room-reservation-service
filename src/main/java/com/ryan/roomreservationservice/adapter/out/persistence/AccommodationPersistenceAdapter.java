package com.ryan.roomreservationservice.adapter.out.persistence;

import com.ryan.roomreservationservice.adapter.out.persistence.entity.AccommodationJpaRepository;
import com.ryan.roomreservationservice.adapter.out.persistence.mapper.AccommodationPersistenceMapper;
import com.ryan.roomreservationservice.application.port.out.QueryAccommodationPort;
import com.ryan.roomreservationservice.domain.Accommodation;
import com.ryan.roomreservationservice.domain.record.LocalDateRange;
import com.ryan.roomreservationservice.utils.exception.ErrorMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class AccommodationPersistenceAdapter implements QueryAccommodationPort {

    private final AccommodationPersistenceMapper mapper;
    private final InternalPersistenceAdapter internalPersistenceAdapter;
    private final AccommodationJpaRepository accommodationJpaRepository;

    public Accommodation findOneAccommodationsByRoomNameAndAccommodationPeriod(String roomName, LocalDateRange accommodationPeriod) {
        var roomEntity = this.internalPersistenceAdapter.findOneRoomByName(roomName);

        return this.accommodationJpaRepository.findOneAccommodationsByRoomAndAccommodationPeriod(roomEntity, accommodationPeriod.start(), accommodationPeriod.end())
                .map(mapper::mapToAccommodation)
                .orElseThrow(() -> new IllegalArgumentException(ErrorMessage.NOT_FOUNT_ACCOMMODATION));
    }
}
