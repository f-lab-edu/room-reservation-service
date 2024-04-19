package com.ryan.roomreservationservice.repository.validator;

import com.ryan.roomreservationservice.domain.Member;
import com.ryan.roomreservationservice.util.enums.CommonStatusCode;
import com.ryan.roomreservationservice.util.enums.ErrorType;
import com.ryan.roomreservationservice.util.exception.CommonException;
import com.ryan.roomreservationservice.util.exception.ErrorMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class MemberValidator {

    private final PasswordEncoder passwordEncoder;

    public void assertMemberExist(Optional<Member> member) {
        member.ifPresent(m -> {
            throw CommonException.builder()
                    .errorType(ErrorType.DEVELOPER)
                    .status(CommonStatusCode.FAIL.getStatusCode())
                    .clientErrorMessage(ErrorMessage.EXIST_MEMBER)
                    .build();
        });
    }

    public Member assertMemberNotExist(Optional<Member> member) {
        return member.orElseThrow(() -> CommonException.builder()
                .errorType(ErrorType.DEVELOPER)
                .status(CommonStatusCode.FAIL.getStatusCode())
                .clientErrorMessage(ErrorMessage.NOT_EXIST_MEMBER)
                .build());
    }

    public void assertPasswordMissMatches(String rawPassword, String encodedPassword) {
        if (!passwordEncoder.matches(rawPassword, encodedPassword))
            throw CommonException.builder()
                    .errorType(ErrorType.DEVELOPER)
                    .status(CommonStatusCode.FAIL.getStatusCode())
                    .clientErrorMessage(ErrorMessage.PASSWORD_NOT_MATCH)
                    .build();
    }
}
