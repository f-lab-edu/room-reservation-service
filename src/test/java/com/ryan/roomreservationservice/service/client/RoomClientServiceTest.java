package com.ryan.roomreservationservice.service.client;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

@DisplayName("고객 객실 테스트")
public class RoomClientServiceTest {

    @BeforeEach
    void init() {

    }

    @Nested
    class Room {
        @ParameterizedTest
        @ValueSource(strings = "2024-01-30")
        public void 당월_예약가능_객실조회(String date) {
            System.out.println("date = " + date);
            // given(준비): 어떠한 데이터가 준비되었을 때

            // when(실행): 어떠한 함수를 실행하면

            // then(검증): 어떠한 결과가 나와야 한다.
        }


        @Test
        void 객실예약_성공() {
            // given(준비): 어떠한 데이터가 준비되었을 때
            String roomName = "객실1";
            String checkInDate = "2024-01-15 15:00:00";
            String checkOutDate = "2024-01-16 11:00:00";

            // when(실행): 어떠한 함수를 실행하면

            // then(검증): 어떠한 결과가 나와야 한다.
        }
    }
}
