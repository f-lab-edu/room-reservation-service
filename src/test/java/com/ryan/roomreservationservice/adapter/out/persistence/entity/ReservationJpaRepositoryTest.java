package com.ryan.roomreservationservice.adapter.out.persistence.entity;

import com.ryan.roomreservationservice.domain.enums.AccommodationStatus;
import com.ryan.roomreservationservice.domain.record.LocalDateRange;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

import java.math.BigDecimal;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest
@ActiveProfiles("memory")
@TestPropertySource(locations = "classpath:application-h2.yaml")
class ReservationJpaRepositoryTest {

    @Autowired
    private MemberJpaRepository memberJpaRepository;

    @Autowired
    private RoomJpaRepository roomJpaRepository;

    @Autowired
    private AccommodationJpaRepository accommodationJpaRepository;

    @Autowired
    private ReservationJpaRepository reservationJpaRepository;

    @Test
    public void 특정_회원의_예약정보_확인하기_검증() {
        // given(준비): 어떠한 데이터가 준비되었을 때
        MemberEntity memberEntity = MemberEntity.builder()
                .memberId(1L)
                .name("Ryan")
                .build();


        RoomEntity roomEntity = RoomEntity.builder()
                .zoneId("Asia/Seoul")
                .name("그린룸")
                .basicPrice(BigDecimal.valueOf(30000))
                .build();

        LocalDateRange accommodationPeriod = LocalDateRange.parse("2024-02-01", "2024-02-03");
        AccommodationEntity accommodationEntity = AccommodationEntity.builder()
                .room(roomEntity)
                .status(AccommodationStatus.AVAILABLE)
                .price(BigDecimal.valueOf(300000))
                .accommodationPeriod(accommodationPeriod)
                .build();

        LocalDateRange reservationDate = LocalDateRange.parse("2024-02-01", "2024-02-03");
        ReservationEntity reservationEntity = ReservationEntity.builder()
                .member(memberEntity)
                .accommodation(accommodationEntity)
                .reservationDate(reservationDate)
                .build();

        this.memberJpaRepository.save(memberEntity);
        this.roomJpaRepository.save(roomEntity);
        this.accommodationJpaRepository.save(accommodationEntity);
        this.reservationJpaRepository.save(reservationEntity);

        // when(실행): 어떠한 함수를 실행하면
        Optional<ReservationEntity> saveReservation = this.reservationJpaRepository.findOneByMemberAndAccommodation(memberEntity, accommodationEntity);

        // then(검증): 어떠한 결과가 나와야 한다.
        assertThat(saveReservation.get()).isNotNull();
        assertThat(saveReservation.get().getMember().getName()).isEqualTo(memberEntity.getName());
    }
}