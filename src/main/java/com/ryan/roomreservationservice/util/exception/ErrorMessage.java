package com.ryan.roomreservationservice.util.exception;

public class ErrorMessage {
    public final static String SERVER_ERROR = "개발팀의 문의해주세요.";
    public final static String CANNOT_BE_NULL_VALUE = "null 값일 수 없습니다.";
    public final static String CANNOT_BE_SET_TO_PAST_DATE = "과거날짜로 설정할 수 없습니다.";
    public final static String CANNOT_BE_EARLIER_THAN_THE_START_DATE = "시작날짜 보다 이전일 수 없습니다.";
    public final static String PLEASE_RESERVATION_PERSON_INFORMATION_CORRECTLY = "예약자 정보를 올바르게 입력해주세요.";
    public final static String RESERVATION_CURRENTLY_UNAVAILABLE = "현재 예약이 불가능합니다.";
    public final static String NOT_EXIST_ROOM = "객실이 존재하지 않습니다.";
    public final static String NOT_TRANSITION_TO_PENDING = "예약 대기 상태로 변경할 수 없습니다.";
    public final static String NOT_CONFIRM_RESERVATION = "예약 완료 상태로 변경할 수 없습니다.";
    public static final String EXIST_MEMBER = "회원이 존재합니다.";
    public static final String NOT_EXIST_MEMBER = "회원이 존재하지 않습니다.";
    public static final String NOT_EXIST_UNAUTHORIZED = "유효한 인증 자격 증명이 없습니다.";
    public final static String PASSWORD_NOT_MATCH = "비밀번호가 틀렸습니다.";
}
