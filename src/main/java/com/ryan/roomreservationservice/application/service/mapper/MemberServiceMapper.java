package com.ryan.roomreservationservice.application.service.mapper;

import com.ryan.roomreservationservice.domain.Member;
import org.springframework.stereotype.Component;

@Component
public class MemberServiceMapper {

    public Member mapToMember(String userId, String password, String name) {
        return Member.builder()
                .userId(userId)
                .password(password)
                .name(name)
                .build();
    }
}
