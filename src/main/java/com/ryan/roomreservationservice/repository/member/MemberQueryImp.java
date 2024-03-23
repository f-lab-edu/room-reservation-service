package com.ryan.roomreservationservice.repository.member;

import com.ryan.roomreservationservice.domain.Member;
import com.ryan.roomreservationservice.persistence.MemberJpaRepository;
import com.ryan.roomreservationservice.service.member.MemberQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class MemberQueryImp implements MemberQuery {

    private final MemberJpaRepository memberJpaRepository;

    @Override
    public Optional<Member> findByEmail(String email) {
        return this.memberJpaRepository.findByEmail(email);
    }
}
