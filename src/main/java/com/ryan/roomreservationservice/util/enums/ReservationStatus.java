package com.ryan.roomreservationservice.util.enums;

import lombok.Getter;

/**
 * @author Ryan
 * @description - 객실 예약상태 <br/>
 *  BLOCK: 예약정지 <br/>
 *  AVAILABLE: 예약가능 <br/>
 *  PENDING: 예약대기 <br/>
 *  CONFIRMED: 예약완료
 */
@Getter
public enum ReservationStatus {
    BLOCK("BLOCK"),
    AVAILABLE("AVAILABLE"),
    PENDING("PENDING"),
    CONFIRMED("CONFIRMED");

    private String status;

    ReservationStatus(String status) {
        this.status = status;
    }

    public boolean isAvailableStatus() {
        return this == AVAILABLE;
    }

    public boolean isPendingStatus() {
        return this == PENDING;
    }

}
