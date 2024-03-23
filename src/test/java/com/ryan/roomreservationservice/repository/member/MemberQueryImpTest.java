package com.ryan.roomreservationservice.repository.member;

import com.ryan.roomreservationservice.domain.Member;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MemberQueryImpTest {

    private MemberQueryImp memberQueryImp;
    private Member member;

    @BeforeEach
    void init() {
        this.member = Member.builder()
                .email("ryan@gamil.com")
                .password("")
                .name("Ryan")
                .phone("01012345678")
                .build();
    }

    @Test
    public void 이메일정보를_통해_회원조회() {
        // given(준비): 어떠한 데이터가 준비되었을 때
        String email = "ryan@gmail.com";

        this.memberQueryImp.findByEmail(email);
        // when(실행): 어떠한 함수를 실행하면

        // then(검증): 어떠한 결과가 나와야 한다.
    }

}