package com.ryan.roomreservationservice.adapter.out.persistence;

import com.ryan.roomreservationservice.adapter.out.persistence.entity.MemberJpaRepository;
import com.ryan.roomreservationservice.adapter.out.persistence.mapper.MemberPersistenceMapper;
import com.ryan.roomreservationservice.application.port.out.CommandMemberPort;
import com.ryan.roomreservationservice.application.port.out.QueryMemberPort;
import com.ryan.roomreservationservice.domain.Member;
import com.ryan.roomreservationservice.utils.exception.ErrorMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class MemberPersistenceAdapter implements QueryMemberPort, CommandMemberPort {
    private final MemberJpaRepository memberJpaRepository;
    private final MemberPersistenceMapper mapper;

    @Override
    public Member findOneByName(String name) {
        return this.memberJpaRepository.findByName(name)
                .map(this.mapper::mapToMember)
                .orElseThrow(() -> new IllegalArgumentException(ErrorMessage.NOT_FOUND_MEMBER));
    }

    @Override
    public Optional<Member> findOneByUserId(String userId) {
        return this.memberJpaRepository.findByUserId(userId)
                .map(this.mapper::mapToMember);
    }

    @Override
    public void save(Member member) {
        this.memberJpaRepository.save(this.mapper.mapToMemberEntity(member));
    }
}
