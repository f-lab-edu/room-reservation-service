package com.ryan.roomreservationservice.adapter.out.persistence.entity;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentHistoryJpaRepository extends JpaRepository<PaymentHistoryEntity, Long> {
}
