package com.ryan.roomreservationservice.service.member;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

public class MemberCommand {

    @Getter
    @ToString
    public static class SignUpRequest {
        private String email;
        private String password;
        private String phone;
        private String name;

        @Builder
        public SignUpRequest(String email, String password, String phone, String name) {
            this.email = email;
            this.password = password;
            this.phone = phone;
            this.name = name;
        }
    }

    @Getter
    @ToString
    public static class SignInRequest {
        private String email;
        private String password;

        @Builder
        public SignInRequest(String email, String password) {
            this.email = email;
            this.password = password;
        }
    }
}
