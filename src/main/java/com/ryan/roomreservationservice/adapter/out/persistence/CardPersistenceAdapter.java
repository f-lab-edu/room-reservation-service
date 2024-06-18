package com.ryan.roomreservationservice.adapter.out.persistence;

import com.ryan.roomreservationservice.application.port.out.QueryCardPort;
import com.ryan.roomreservationservice.domain.Card;
import com.ryan.roomreservationservice.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class CardPersistenceAdapter implements QueryCardPort {


    @Override
    public List<Card> findByMember(Member member) {
        return List.of();
    }
}
