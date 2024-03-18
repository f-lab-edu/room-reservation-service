package com.ryan.roomreservationservice.repository.accommodation;

import com.ryan.roomreservationservice.domain.Accommodation;
import com.ryan.roomreservationservice.domain.DateRange;
import com.ryan.roomreservationservice.domain.Room;
import com.ryan.roomreservationservice.persistence.AccommodationJpaRepository;
import com.ryan.roomreservationservice.service.accommodation.AccommodationQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class AccommodationQueryImp implements AccommodationQuery {
    private final AccommodationJpaRepository accommodationJpaRepository;

    @Override
    public List<Accommodation> findByRoomAndReservationDateWithPessimisticLock(Room room, DateRange dateRange) {
        return this.accommodationJpaRepository.findByRoomAndReservationDateWithPessimisticLock(room, dateRange.getStart(), dateRange.getEnd());
    }
}
