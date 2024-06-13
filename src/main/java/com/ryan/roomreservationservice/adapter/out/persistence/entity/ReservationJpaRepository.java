package com.ryan.roomreservationservice.adapter.out.persistence.entity;

import com.ryan.roomreservationservice.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReservationJpaRepository extends JpaRepository<ReservationEntity, Long> {

    @Query("select r from ReservationEntity r left join fetch MemberEntity m where r.memberEntity = :member")
    List<ReservationEntity> findByMember(@Param("member") Member member);
}
