package com.ryan.roomreservationservice.application.port.out;

import com.ryan.roomreservationservice.domain.Card;
import com.ryan.roomreservationservice.domain.Member;

import java.util.List;

public interface QueryCardPort {
    List<Card> findByMember(Member member);
}
