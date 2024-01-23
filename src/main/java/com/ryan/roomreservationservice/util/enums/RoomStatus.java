package com.ryan.roomreservationservice.util.enums;

import lombok.Getter;

/**
 * @author Ryan
 * @description - 객실 상태 <br/>
 *  EXPOSURE_POSSIBLE: 노출 가능  <br/>
 *  EXPOSURE_NOT_POSSIBLE: 노출 불가능 <br/>
 *  RESERVATION_NOT_POSSIBLE: 예약 불가능 <br/>
 */

@Getter
public enum RoomStatus {
    EXPOSURE_POSSIBLE("EXPOSURE_POSSIBLE"),
    EXPOSURE_NOT_POSSIBLE("EXPOSURE_NOT_POSSIBLE"),
    RESERVATION_NOT_POSSIBLE("RESERVATION_NOT_POSSIBLE");

    private String status;

    RoomStatus(String status) {
        this.status = status;
    }
}
