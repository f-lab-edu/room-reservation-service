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
    private DateRange reservationDate;

    // 입/퇴실 정보
    private TimeRange checkInOut;

    /**
     * @author Ryan
     * @description 현재 예약하고 싶은 객실 예약 가능 여부를 확인합니다.
     * - isAfter: 현재 Instant(A)가 매개변수로 전달 된 Instant(B)보다 "이전"인지 확인 (Ex: A < B)
     * - isBefore: 현재 Instant(A)가 매개변수로 전달 된 Instant(B)보다 "이후"인지 확인 (EX: A > B)
     */
    public boolean checkAvailabilityStatus(Instant checkInDate, Instant checkOutDate) {
        if(Instant.now().isAfter(checkInDate)) return false;

        if(this.reservationDate.getStart().equals(checkInDate) && this.reservationDate.getEnd().equals(checkOutDate)) return false;

        if(this.reservationDate.getStart().isAfter(checkInDate) && this.reservationDate.getStart().isBefore(checkOutDate)) return false;

        if(this.reservationDate.getEnd().isAfter(checkInDate) && this.reservationDate.getEnd().isBefore(checkOutDate)) return false;

        return true;
    }

}
