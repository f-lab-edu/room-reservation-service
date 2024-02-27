package com.ryan.roomreservationservice.util.enums;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ReservationStatusTest {
    
    @Test
    public void 필드_비교_테스트() {
        // given(준비): 어떠한 데이터가 준비되었을 때
        AccommodationAvailability pending = null;

        // when(실행): 어떠한 함수를 실행하면
        // then(검증): 어떠한 결과가 나와야 한다.
        assertFalse(pending == AccommodationAvailability.PENDING);
        assertThrows(NullPointerException.class, () -> pending.getStatus().equals(AccommodationAvailability.PENDING.getStatus()));
    }

}