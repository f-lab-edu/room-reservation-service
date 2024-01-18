package com.ryan.roomreservationservice.domain;

import lombok.Builder;
import lombok.Getter;

import java.time.Instant;

@Builder
@Getter
public class RoomReservation {

    // 고유 키
    private Long roomReservationId;

    // 객실 고유 키
    private Long roomId;

    // 예약 상태(Ex - 예약정지: BLOCK, 예약대기: PENDING, 예약완료: CONFIRMED)
    private String reservationStatus;

    // 예약날짜 정보
    private DateRange dateRange; // 데이터 레인지 -> 겹치니? 겹치지 않니?? 교집합 인터셉션

    // 입/퇴실 정보
    private TimeRange timeRange; // 타임 레인지 범위와 관련된 변수명 , 불변식 코드 작성 및 테스트!!


    /**
     * @author Ryan
     * @description 현재 예약하고 싶은 객실 예약 가능 여부를 확인합니다.
     * - isAfter: 현재 Instant(A)가 매개변수로 전달 된 Instant(B)보다 "이전"인지 확인 (Ex: A < B)
     * - isBefore: 현재 Instant(A)가 매개변수로 전달 된 Instant(B)보다 "이후"인지 확인 (EX: A > B)
     */
    public boolean checkAvailabilityStatus(Instant checkInDate, Instant checkOutDate) {
        if(Instant.now().isAfter(checkInDate)) return false;

        if(this.dateRange.getStartDate().equals(checkInDate) && this.dateRange.getEndDate().equals(checkOutDate)) return false;

        if(this.dateRange.getStartDate().isAfter(checkInDate) && this.dateRange.getStartDate().isBefore(checkOutDate)) return false;

        if(this.dateRange.getEndDate().isAfter(checkInDate) && this.dateRange.getEndDate().isBefore(checkOutDate)) return false;

        return true;
    }

}
