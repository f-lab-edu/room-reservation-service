package com.ryan.roomreservationservice.service.member;

import com.ryan.roomreservationservice.domain.Member;

public interface MemberStore {
    Member save(String email, String password, String name, String phone);
}
