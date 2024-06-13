package com.ryan.roomreservationservice.adapter.out.persistence.entity;

import com.ryan.roomreservationservice.domain.enums.AccommodationStatus;
import com.ryan.roomreservationservice.domain.record.LocalDateRange;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("memory")
@TestPropertySource(locations = "classpath:application-h2.yaml")
class AccommodationJpaRepositoryTest {

    @Autowired
    private RoomJpaRepository roomJpaRepository;

    @Autowired
    private AccommodationJpaRepository accommodationJpaRepository;

    @Test
    public void 등록된_숙박정보_조회하기() {
        // given(준비): 어떠한 데이터가 준비되었을 때
        String zoneId = "Asia/Seoul";
        String roomName = "그린룸";
        BigDecimal basicPrice = BigDecimal.valueOf(300000);

        RoomEntity roomEntity = RoomEntity.builder()
                .zoneId(zoneId)
                .name(roomName)
                .basicPrice(basicPrice)
                .build();

        this.roomJpaRepository.save(roomEntity);

        BigDecimal price = BigDecimal.valueOf(300000);
        String start = "2024-02-01";
        String end = "2024-02-03";
        LocalDateRange accommodationPeriod = LocalDateRange.parse(start, end);

        AccommodationEntity accommodationEntity = AccommodationEntity.builder()
                .roomEntity(roomEntity)
                .status(AccommodationStatus.AVAILABLE)
                .price(price)
                .accommodationPeriod(accommodationPeriod)
                .build();

        this.accommodationJpaRepository.save(accommodationEntity);

        // when(실행): 어떠한 함수를 실행하면
        AccommodationEntity accommodation = this.accommodationJpaRepository.findOneAccommodationsByRoomAndAccommodationPeriod(roomEntity, accommodationPeriod.start(), accommodationPeriod.end())
                .orElseThrow();

        // then(검증): 어떠한 결과가 나와야 한다.
        assertThat(accommodation).isNotNull();
        assertThat(accommodation.getRoomEntity().getName()).isEqualTo(roomName);
    }

    @Test
    public void 등록되지_않은_날짜조회() {
        // given(준비): 어떠한 데이터가 준비되었을 때
        String zoneId = "Asia/Seoul";
        String roomName = "그린룸";
        BigDecimal basicPrice = BigDecimal.valueOf(300000);

        RoomEntity roomEntity = RoomEntity.builder()
                .zoneId(zoneId)
                .name(roomName)
                .basicPrice(basicPrice)
                .build();

        this.roomJpaRepository.save(roomEntity);

        BigDecimal price = BigDecimal.valueOf(300000);
        String start = "2024-02-01";
        String end = "2024-02-03";
        LocalDateRange accommodationPeriod = LocalDateRange.parse(start, end);

        AccommodationEntity accommodationEntity = AccommodationEntity.builder()
                .roomEntity(roomEntity)
                .status(AccommodationStatus.AVAILABLE)
                .price(price)
                .accommodationPeriod(accommodationPeriod)
                .build();

        this.accommodationJpaRepository.save(accommodationEntity);

        LocalDate fakeStart = LocalDate.parse("2024-02-02");

        // when(실행): 어떠한 함수를 실행하면
        Optional<AccommodationEntity> accommodation = this.accommodationJpaRepository.findOneAccommodationsByRoomAndAccommodationPeriod(roomEntity, fakeStart, accommodationPeriod.end());

        // then(검증): 어떠한 결과가 나와야 한다.
        assertThat(accommodation.isEmpty()).isTrue();
    }
}