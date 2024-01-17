package com.ryan.roomreservationservice.domain;

import lombok.Builder;
import lombok.Getter;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.LocalTime;

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
    private ReservationDate reservationDate;

    // 입/퇴실 정보
    private CheckInOutTime checkInOutTime;

    // 성인 인원수
    private Integer numberOfAdults;

    // 아동 인원수
    private Integer numberOfChildren;

    // 유아 인원수
    private Integer numberOfInfants;

    // 기본 객실 가격
    private Integer defaultRoomPrice;

    // 총 결제 가격
    private Integer totalPricePaid;

    // 예약자 이름
    private String reservationPerson;

    // 예약자 휴대폰
    private String reservationPersonPhone;

    // 비상 연락처
    private String emergencyPhone;

    // 이메일
    private String email;

    // 도착예정 시간
    private Instant estimatedTimeOfArrival;

    // 예약 요청사항
    private String reservationRequest;

    // 유입 경로
    private String funnels;

    /**
     * @author Ryan
     * @description 현재 예약하고 싶은 객실 예약 가능 여부를 확인합니다.
     * - isAfter: 현재 Instant(A)가 매개변수로 전달 된 Instant(B)보다 "이전"인지 확인 (Ex: A < B)
     * - isBefore: 현재 Instant(A)가 매개변수로 전달 된 Instant(B)보다 "이후"인지 확인 (EX: A > B)
     */
    public boolean checkAvailabilityStatus(Instant checkInDate, Instant checkOutDate) {
        if(Instant.now().isAfter(checkInDate)) return false;

        if(this.reservationDate.getReservationStartDate().equals(checkInDate) && this.reservationDate.getReservationEndDate().equals(checkOutDate)) return false;

        if(this.reservationDate.getReservationStartDate().isAfter(checkInDate) && this.reservationDate.getReservationStartDate().isBefore(checkOutDate)) return false;

        if(this.reservationDate.getReservationEndDate().isAfter(checkInDate) && this.reservationDate.getReservationEndDate().isBefore(checkOutDate)) return false;

        return true;
    }

}
