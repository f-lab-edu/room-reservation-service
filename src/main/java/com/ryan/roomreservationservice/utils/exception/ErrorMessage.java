package com.ryan.roomreservationservice.utils.exception;

public class ErrorMessage {
    public final static String SERVER_ERROR = "개발팀의 문의해주세요.";
    public final static String UNAVAILABLE_RESERVATION = "예약이 불가능합니다.";
    public final static String CANNOT_BE_NULL_VALUE = "null 값일 수 없습니다.";
    public final static String CANNOT_BE_EARLIER_THAN_THE_START_DATE = "종료 날짜는 시작 날짜보다 이전일 수 없습니다.";
    public final static String PAYMENT_AMOUNT_NOT_MATCH_PRICE = "예약 금액과 결제 금액이 일치하지 않습니다.";
    public final static String CANCEL_REQUEST_DATE_MUST_BE_BEFORE_CHECK_IN = "취소 요청 날짜는 체크인 전이어야 합니다";
    public final static String NOT_FOUND_CATEGORY = "카테고리를 찾을 수 없습니다.";
    public final static String PLEASE_END_CORRECT_CATEGORY = "올바른 카테고리를 보내주세요.";
    public final static String PLEASE_END_CORRECT_SUBCATEGORY = "올바른 서브 카테고리를 보내주세요.";
    public final static String NOT_FOUND_MEMBER = "회원을 찾을 수 없습니다.";
    public static final String EXIST_MEMBER = "회원이 존재합니다.";
    public static final String NOT_EXIST_MEMBER = "회원이 존재하지 않습니다.";
    public final static String MISS_MATCH_PW = "비밀번호가 틀렸습니다.";
    public final static String NOT_FOUND_ACCOMMODATION = "숙박 정보가 존재하지 않습니다.";
    public final static String NOT_FOUND_ROOM = "객실이 존재하지 않습니다.";
    public final static String NOT_FOUND_RESERVATION_BY_MEMBER = "회원의 예약 정보를 찾을 수 없습니다.";
    public static final String NOT_EXIST_UNAUTHORIZED = "유효한 인증 자격 증명이 없습니다.";
    public static final String REQUIRED_VALUE_EMAIL = "이메일은는 필수입니다.";
    public static final String REQUIRED_VALUE_PASSWORD = "비밀번호는 필수입니다.";
    public static final String NOT_PASSWORD_SIZE ="비밀번호는 8자 이상 16자 이하로 입력해주세요.";
    public static final String NOT_EXIST_PASSWORD_UNAUTHORIZED = "비밀번호는 영문 대,소문자와 숫자, 특수기호가 적어도 1개 이상씩 포함된 8자 ~ 16자의 비밀번호여야 합니다.";
    public static final String REQUIRED_NAME = "성함은 필수입니다.";
    public final static String NOT_REGEXP_RESIDENT_ID_NUMBER_UNAUTHORIZED = "올바른 주민등록 번호 약식을 작성해주세요.";

}
