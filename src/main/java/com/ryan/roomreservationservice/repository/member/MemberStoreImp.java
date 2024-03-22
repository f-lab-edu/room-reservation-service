package com.ryan.roomreservationservice.repository.member;

import com.ryan.roomreservationservice.domain.Member;
import com.ryan.roomreservationservice.persistence.MemberJpaRepository;
import com.ryan.roomreservationservice.service.member.MemberStore;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class MemberStoreImp implements MemberStore {

    private final MemberJpaRepository memberJpaRepository;

    @Override
    public Member save(String email, String password, String name, String phone) {
        Member member = Member.builder()
                .email(email)
                .password(password)
                .name(name)
                .phone(phone)
                .build();

        return this.memberJpaRepository.save(member);
    }
}
