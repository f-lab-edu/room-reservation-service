package com.ryan.roomreservationservice.domain;

import com.ryan.roomreservationservice.domain.enums.AccommodationStatus;
import com.ryan.roomreservationservice.utils.exception.ErrorMessage;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.ZoneId;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class AccommodationTest {

    @Test
    public void 예약_가능한_상태의_숙박_예약확인() {
        // given(준비): 어떠한 데이터가 준비되었을 때
        ZoneId zoneId = ZoneId.of("Asia/Seoul");
        String roomName = "그린룸";
        long price = 300000;

        Room room = new Room(zoneId, roomName, price);
        Accommodation accommodation = new Accommodation(room, AccommodationStatus.AVAILABLE);

        // when(실행): 어떠한 함수를 실행하면
        accommodation.confirmReservation(accommodation);

        // then(검증): 어떠한 결과가 나와야 한다.
        assertThat(accommodation.getStatus()).isEqualTo(AccommodationStatus.PENDING);
    }

    @Test
    public void 예약_불가능한_상태의_숙박_예약확인() {
        // given(준비): 어떠한 데이터가 준비되었을 때
        ZoneId zoneId = ZoneId.of("Asia/Seoul");
        String roomName = "그린룸";
        long price = 300000;

        Room room = new Room(zoneId, roomName, price);

        Accommodation block = new Accommodation(room, AccommodationStatus.BLOCK);
        Accommodation pending = new Accommodation(room, AccommodationStatus.PENDING);
        Accommodation confirmed = new Accommodation(room, AccommodationStatus.CONFIRMED);

        // when(실행): 어떠한 함수를 실행하면
        IllegalArgumentException blockException = assertThrows(IllegalArgumentException.class, () -> block.confirmReservation(block));
        IllegalArgumentException pendingException = assertThrows(IllegalArgumentException.class, () -> pending.confirmReservation(pending));
        IllegalArgumentException confirmException = assertThrows(IllegalArgumentException.class, () -> confirmed.confirmReservation(confirmed));

        // then(검증): 어떠한 결과가 나와야 한다.
        assertThat(blockException.getMessage()).isEqualTo(ErrorMessage.UNAVAILABLE_RESERVATION);
        assertThat(pendingException.getMessage()).isEqualTo(ErrorMessage.UNAVAILABLE_RESERVATION);
        assertThat(confirmException.getMessage()).isEqualTo(ErrorMessage.UNAVAILABLE_RESERVATION);
    }

    @Test
    public void 결제금액_계산_검증() {
        // given(준비): 어떠한 데이터가 준비되었을 때
        ZoneId zoneId = ZoneId.of("Asia/Seoul");
        String roomName = "그린룸";
        long price = 300000;

        Room room = new Room(zoneId, roomName, price);
        Accommodation accommodation = new Accommodation(room, AccommodationStatus.AVAILABLE);

        LocalDate start = LocalDate.parse("2024-02-01");
        LocalDate end = LocalDate.parse("2024-02-03");

        DateRange reservationDate = new DateRange(start, end);

        // when(실행): 어떠한 함수를 실행하면
        long paymentAmount = accommodation.getPaymentAmount(reservationDate);

        // then(검증): 어떠한 결과가 나와야 한다.
        assertThat(paymentAmount).isEqualTo(600000);
    }

}