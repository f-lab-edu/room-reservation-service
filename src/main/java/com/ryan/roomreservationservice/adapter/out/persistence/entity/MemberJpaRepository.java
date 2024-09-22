package com.ryan.roomreservationservice.adapter.out.persistence.entity;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberJpaRepository extends JpaRepository<MemberEntity, Long> {
    Optional<MemberEntity> findByName(String name);
    Optional<MemberEntity> findByUserId(String userId);
}
