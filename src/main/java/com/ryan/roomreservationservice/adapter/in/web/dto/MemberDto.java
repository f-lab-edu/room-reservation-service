package com.ryan.roomreservationservice.adapter.in.web.dto;

import com.ryan.roomreservationservice.utils.Constant;
import com.ryan.roomreservationservice.utils.exception.ErrorMessage;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

public class MemberDto {

    @Getter
    @ToString
    @NoArgsConstructor
    public static class SignUpRequest {

        @Schema(description = "유저 아이디", example = "tlsgksrnr12")
        @NotBlank(message = ErrorMessage.REQUIRED_VALUE_EMAIL)
        private String userId;

        @Schema(description = "유저 비밀번호 정규식(비밀번호는 영문 대,소문자와 숫자, 특수기호가 적어도 1개 이상씩 포함된 8자 ~ 16자)", example = "1234qweR!!")
        @NotBlank(message = ErrorMessage.REQUIRED_VALUE_PASSWORD)
        @Size(min = Constant.MIN_SIZE_PASSWORD, max = Constant.MAX_SIZE_PASSWORD, message = ErrorMessage.NOT_PASSWORD_SIZE)
        @Pattern(regexp = Constant.REGEXP_PASSWORD, message = ErrorMessage.NOT_EXIST_PASSWORD_UNAUTHORIZED)
        private String password;

        @Schema(description = "유저 이름", example = "홍길동")
        @NotBlank(message = ErrorMessage.REQUIRED_NAME)
        private String name;

        @Schema(description = "유저 휴대폰 번호", example = "010-1234-5678")
        @Pattern(regexp = Constant.REGEXP_PHONE, message = ErrorMessage.NOT_REGEXP_RESIDENT_ID_NUMBER_UNAUTHORIZED)
        private String phone;

    }

    @Getter
    @ToString
    @NoArgsConstructor
    public static class SignInRequest {
        @Schema(description = "유저 아이디", example = "tlsgksrnr12")
        @NotBlank(message = ErrorMessage.REQUIRED_VALUE_EMAIL)
        private String userId;

        @Schema(description = "유저 비밀번호 정규식(비밀번호는 영문 대,소문자와 숫자, 특수기호가 적어도 1개 이상씩 포함된 8자 ~ 16자)", example = "1234qweR!!")
        @NotBlank(message = ErrorMessage.REQUIRED_VALUE_PASSWORD)
        @Size(min = Constant.MIN_SIZE_PASSWORD, max = Constant.MAX_SIZE_PASSWORD, message = ErrorMessage.NOT_PASSWORD_SIZE)
        @Pattern(regexp = Constant.REGEXP_PASSWORD, message = ErrorMessage.NOT_EXIST_PASSWORD_UNAUTHORIZED)
        private String password;
    }

    @Getter
    @Builder
    @ToString
    public static class SignInResponse {
        @Schema(description = "로그인 성공 후 발급 받는 토큰정보")
        public String accessToken;
    }
}