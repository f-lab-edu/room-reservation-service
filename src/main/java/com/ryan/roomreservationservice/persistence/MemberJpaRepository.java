package com.ryan.roomreservationservice.persistence;

import com.ryan.roomreservationservice.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberJpaRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByEmail(String email);
}
