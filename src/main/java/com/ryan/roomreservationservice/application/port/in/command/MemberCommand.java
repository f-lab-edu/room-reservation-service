package com.ryan.roomreservationservice.application.port.in.command;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

public class MemberCommand {

    @Getter
    @Builder
    @ToString
    public static class SignUpCommand {
        private String userId;
        private String password;
        private String name;
    }

    @Getter
    @Builder
    @ToString
    public static class SignInCommand {
        private String userId;
        private String password;
    }
}
