package com.ryan.roomreservationservice.application.port.out;

import com.ryan.roomreservationservice.adapter.out.persistence.entity.MemberEntity;

public interface QueryMemberPort {
    MemberEntity findOneByName(String name);
}
