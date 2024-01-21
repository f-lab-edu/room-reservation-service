package com.ryan.roomreservationservice.util.enums;

import com.ryan.roomreservationservice.util.exception.ErrorMessage;
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
public enum ReservationStatusEnum {
    BLOCK("BLOCK"),
    AVAILABLE("AVAILABLE"),
    PENDING("PENDING"),
    CONFIRMED("CONFIRMED");

    private String status;

    ReservationStatusEnum(String status) {
        this.status = status;
    }

    public boolean isAvailableStatus() {
        if(!this.status.equals(this.AVAILABLE.getStatus())){
            throw new IllegalArgumentException(ErrorMessage.RESERVATION_CURRENTLY_UNAVAILABLE);
        }

        return true;
    }

}
