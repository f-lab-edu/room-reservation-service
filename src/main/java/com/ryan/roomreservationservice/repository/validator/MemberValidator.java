package com.ryan.roomreservationservice.repository.validator;

import com.ryan.roomreservationservice.domain.Member;
import com.ryan.roomreservationservice.util.enums.CommonStatusCode;
import com.ryan.roomreservationservice.util.enums.ErrorType;
import com.ryan.roomreservationservice.util.exception.CommonException;
import com.ryan.roomreservationservice.util.exception.ErrorMessage;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class MemberValidator {
    public void assertMemberExist(Optional<Member> member) {
        member.ifPresent(m -> {
            throw CommonException.builder()
                    .errorType(ErrorType.DEVELOPER)
                    .status(CommonStatusCode.FAIL.getStatusCode())
                    .clientErrorMessage(ErrorMessage.EXIST_MEMBER)
                    .build();
        });
    }
}
