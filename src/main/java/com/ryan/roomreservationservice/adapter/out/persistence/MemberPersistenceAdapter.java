package com.ryan.roomreservationservice.adapter.out.persistence;

import com.ryan.roomreservationservice.adapter.out.persistence.entity.MemberJpaRepository;
import com.ryan.roomreservationservice.adapter.out.persistence.mapper.MemberPersistenceMapper;
import com.ryan.roomreservationservice.application.port.out.QueryMemberPort;
import com.ryan.roomreservationservice.domain.Member;
import com.ryan.roomreservationservice.utils.exception.ErrorMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class MemberPersistenceAdapter implements QueryMemberPort {
    private final MemberJpaRepository memberJpaRepository;
    private final MemberPersistenceMapper mapper;

    @Override
    public Member findOneByName(String name) {
        return this.memberJpaRepository.findByName(name)
                .map(mapper::mapToMember)
                .orElseThrow(() -> new IllegalArgumentException(ErrorMessage.NOT_FOUNT_MEMBER));
    }
}
