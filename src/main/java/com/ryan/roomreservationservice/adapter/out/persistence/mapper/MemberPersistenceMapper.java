package com.ryan.roomreservationservice.adapter.out.persistence.mapper;

import com.ryan.roomreservationservice.adapter.out.persistence.entity.MemberEntity;
import com.ryan.roomreservationservice.domain.Member;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class MemberPersistenceMapper {

    public Member mapToMember(MemberEntity memberEntity) {
        return Member.builder()
                .name(memberEntity.getName())
                .paymentHistories(new ArrayList<>())
                .cards(new ArrayList<>())
                .build();
    }

    public MemberEntity mapToMemberEntity(Member member) {
        return MemberEntity.builder()
                .memberId(member.getMemberId())
                .name(member.getName())
                .build();
    }
}
