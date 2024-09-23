package com.ryan.roomreservationservice.application.service.validator;

import com.ryan.roomreservationservice.domain.Member;
import com.ryan.roomreservationservice.utils.exception.CommonException;
import com.ryan.roomreservationservice.utils.exception.ErrorMessage;
import com.ryan.roomreservationservice.utils.exception.ErrorType;
import com.ryan.roomreservationservice.utils.exception.StatusCode;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class MemberValidator {
    private final PasswordEncoder passwordEncoder;

    public void assertMemberExist(Optional<Member> member) {
        member.ifPresent(d -> {
            throw CommonException.builder()
                    .errorType(ErrorType.DEVELOPER)
                    .status(StatusCode.FAIL.getStatusCode())
                    .clientErrorMessage(ErrorMessage.EXIST_MEMBER)
                    .build();
        });
    }

    public Member assertMemberNotExist(Optional<Member> member) {
        return member.orElseThrow(() -> CommonException.builder()
                .errorType(ErrorType.DEVELOPER)
                .status(StatusCode.FAIL.getStatusCode())
                .clientErrorMessage(ErrorMessage.NOT_EXIST_MEMBER)
                .build());
    }

    public void assertPasswordMissMatches(String newPassword, String oldPassword) {
        if (!passwordEncoder.matches(newPassword, oldPassword))
            throw CommonException.builder()
                    .errorType(ErrorType.DEVELOPER)
                    .status(StatusCode.FAIL.getStatusCode())
                    .clientErrorMessage(ErrorMessage.MISS_MATCH_PW)
                    .build();
    }
}
