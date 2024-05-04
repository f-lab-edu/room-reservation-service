package com.ryan.roomreservationservice.domain;

import com.ryan.roomreservationservice.domain.enums.AccommodationStatus;
import com.ryan.roomreservationservice.utils.exception.ErrorMessage;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class AccommodationTest {

    @Test
    public void 예약_가능한_상태의_숙박_예약확인() {
        // given(준비): 어떠한 데이터가 준비되었을 때
        Accommodation accommodation = new Accommodation(AccommodationStatus.AVAILABLE);

        // when(실행): 어떠한 함수를 실행하면
        accommodation.confirmReservation(accommodation);

        // then(검증): 어떠한 결과가 나와야 한다.
        assertThat(accommodation.getStatus()).isEqualTo(AccommodationStatus.PENDING);
    }

    @Test
    public void 예약_불가능한_상태의_숙박_예약확인() {
        // given(준비): 어떠한 데이터가 준비되었을 때
        Accommodation block = new Accommodation(AccommodationStatus.BLOCK);
        Accommodation pending = new Accommodation(AccommodationStatus.PENDING);
        Accommodation confirmed = new Accommodation(AccommodationStatus.CONFIRMED);

        // when(실행): 어떠한 함수를 실행하면
        IllegalArgumentException blockException = assertThrows(IllegalArgumentException.class, () -> block.confirmReservation(block));
        IllegalArgumentException pendingException = assertThrows(IllegalArgumentException.class, () -> pending.confirmReservation(pending));
        IllegalArgumentException confirmException = assertThrows(IllegalArgumentException.class, () -> confirmed.confirmReservation(confirmed));

        // then(검증): 어떠한 결과가 나와야 한다.
        assertThat(blockException.getMessage()).isEqualTo(ErrorMessage.UNAVAILABLE_RESERVATION);
        assertThat(pendingException.getMessage()).isEqualTo(ErrorMessage.UNAVAILABLE_RESERVATION);
        assertThat(confirmException.getMessage()).isEqualTo(ErrorMessage.UNAVAILABLE_RESERVATION);
    }
  
}