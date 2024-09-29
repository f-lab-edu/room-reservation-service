package com.ryan.roomreservationservice.application.port.out;

import com.ryan.roomreservationservice.domain.Member;

import java.util.Optional;

public interface QueryMemberPort {
    Optional<Member> findOneByUserId(String userId);
}
