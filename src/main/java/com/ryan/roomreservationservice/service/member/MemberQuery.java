package com.ryan.roomreservationservice.service.member;

import com.ryan.roomreservationservice.domain.Member;

import java.util.Optional;

public interface MemberQuery {
    Optional<Member> findByEmail(String email);

}
