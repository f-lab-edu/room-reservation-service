package com.ryan.roomreservationservice.application.port.out;

import com.ryan.roomreservationservice.domain.Member;

public interface CommandMemberPort {
    void save(Member member);
}
