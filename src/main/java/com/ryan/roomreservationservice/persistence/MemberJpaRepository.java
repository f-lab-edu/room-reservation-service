package com.ryan.roomreservationservice.persistence;

import com.ryan.roomreservationservice.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberJpaRepository extends JpaRepository<MemberEntity, Long> {
}
