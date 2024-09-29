package com.ryan.roomreservationservice.adapter.out.persistence.entity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ReservationJpaRepository extends JpaRepository<ReservationEntity, Long> {

    @Query("select r from ReservationEntity r left join fetch r.member m where m = :member")
    List<ReservationEntity> findByMember(@Param("member") MemberEntity member);

    Optional<ReservationEntity> findOneByMemberAndAccommodation(MemberEntity member, AccommodationEntity accommodation);
}
