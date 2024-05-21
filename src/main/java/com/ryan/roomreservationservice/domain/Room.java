package com.ryan.roomreservationservice.domain;

import com.ryan.roomreservationservice.utils.exception.ErrorMessage;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

public class Room {
    private ZoneId zoneId;
    private String name;
    private long price;

    public Room(ZoneId zoneId, String name, long price) {
        this.zoneId = zoneId;
        this.name = name;
        this.price = price;
    }

    public List<Room> showRoomInfos() {
        return List.of();
    }

    public long calculateRoomPaymentAmount(LocalDateRange localDateRange) {
        long reservationPeriod = localDateRange.calculateDayPeriod();
        return this.price * reservationPeriod;
    }

    public long calculateRoomRefundAmount(LocalDate cancelLocalDate, LocalDateRange reservationDate) {
        LocalDate start = reservationDate.start();

        if (start.isBefore(cancelLocalDate))
            throw new IllegalArgumentException(ErrorMessage.CANCEL_REQUEST_DATE_MUST_BE_BEFORE_CHECK_IN);

        long beforeDay = reservationDate.calculatePeriodBeforeStartDate(cancelLocalDate);
        if (3 < beforeDay && beforeDay < 7) {
            BigDecimal refundRate = BigDecimal.valueOf(70).divide(BigDecimal.valueOf(100));
            BigDecimal refundAmount = BigDecimal.valueOf(this.price).multiply(refundRate);

            return refundAmount.setScale(1, RoundingMode.HALF_UP).longValue();
        }

        return this.price;
    }

}
