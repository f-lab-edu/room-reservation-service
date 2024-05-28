package com.ryan.roomreservationservice.repository;

import com.ryan.roomreservationservice.persistence.MemberJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class MemberRepository {
    private final MemberJpaRepository memberJpaRepository;
}
