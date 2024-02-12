package com.ryan.roomreservationservice.service.reservation;

import com.ryan.roomreservationservice.domain.RoomReservation;
import lombok.Getter;
import lombok.ToString;

import java.time.Instant;

/**
 * @author Ryan
 * @description Controller 응답 정보를 담당하는 클래스
 */
public class ReservationInfo {

    @Getter
    @ToString
    public static class GetReservationList {
        private final String zoneCode;
        private final String roomName;
        private final String roomSize;
        private final String reservationStatus;
        private final Instant reservationStartDate;
        private final Instant reservationEndDate;

        public GetReservationList(RoomReservation reservation) {
            this.zoneCode = reservation.getRoom().getZoneCode();
            this.roomName = reservation.getRoom().getRoomName();
            this.roomSize = reservation.getRoom().getRoomSize();
            this.reservationStatus = reservation.getReservationStatus().getStatus();
            this.reservationStartDate = reservation.getReservation().getStart();
            this.reservationEndDate = reservation.getReservation().getEnd();
        }
    }
}
