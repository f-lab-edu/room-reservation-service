package com.ryan.roomreservationservice.persistence;

import com.ryan.roomreservationservice.entity.PaymentHistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentHistoryJpaRepository extends JpaRepository<PaymentHistoryEntity, Long> {
}
