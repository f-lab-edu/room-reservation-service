package com.ryan.roomreservationservice.repository;

import com.ryan.roomreservationservice.persistence.PaymentHistoryJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class PaymentHistoryRepository {
    private final PaymentHistoryJpaRepository historyJpaRepository;
}
