package com.ryan.roomreservationservice.util.enums;

import lombok.Getter;

/**
 * @author Ryan
 * @description - 객실 예약상태 <br/>
 *  CANCEL: 예약취소 <br/>
 *  CONFIRMED: 예약완료
 */
@Getter
public enum ReservationStatus {
    CANCEL("CANCEL"),
    CONFIRMED("CONFIRMED");

    private String status;

    ReservationStatus(String status) {
        this.status = status;
    }

}
