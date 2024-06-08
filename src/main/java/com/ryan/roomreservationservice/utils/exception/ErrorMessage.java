package com.ryan.roomreservationservice.utils.exception;

public class ErrorMessage {
    public final static String UNAVAILABLE_RESERVATION = "예약이 불가능합니다.";
    public final static String CANNOT_BE_NULL_VALUE = "null 값일 수 없습니다.";
    public final static String CANNOT_BE_EARLIER_THAN_THE_START_DATE = "종료 날짜는 시작 날짜보다 이전일 수 없습니다.";
    public final static String PAYMENT_AMOUNT_NOT_MATCH_PRICE = "예약 금액과 결제 금액이 일치하지 않습니다.";
    public final static String CANCEL_REQUEST_DATE_MUST_BE_BEFORE_CHECK_IN = "취소 요청 날짜는 체크인 전이어야 합니다";
    public final static String NOT_FOUND_CATEGORY = "카테고리를 찾을 수 없습니다.";
    public final static String PLEASE_END_CORRECT_CATEGORY = "올바른 카테고리를 보내주세요.";
    public final static String PLEASE_END_CORRECT_SUBCATEGORY = "올바른 서브 카테고리를 보내주세요.";
    public final static String NOT_FOUNT_MEMBER = "회원을 찾을 수 없습니다.";
    public final static String NOT_FOUNT_ACCOMMODATION = "숙박 정보가 존재하지 않습니다.";
    public final static String Not_FOUNT_ROOM = "객실이 존재하지 않습니다.";
}
