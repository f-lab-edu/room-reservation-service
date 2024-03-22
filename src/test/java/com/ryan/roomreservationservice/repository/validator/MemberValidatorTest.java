package com.ryan.roomreservationservice.repository.validator;

import com.ryan.roomreservationservice.domain.Member;
import com.ryan.roomreservationservice.util.exception.CommonException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.junit.jupiter.api.Assertions.*;

class MemberValidatorTest {

    private MemberValidator memberValidator;

    @BeforeEach()
    void init() {
        this.memberValidator = new MemberValidator();
    }

    @Test
    public void 회원이_존재_한다면_예외가_발생한다() {
        // given(준비): 어떠한 데이터가 준비되었을 때
        Optional<Member> member = Optional.ofNullable(Member.builder()
                .email("ryan@gmail.com")
                .password("1234qweR!!")
                .name("라이언")
                .phone("01012345678")
                .build());

        // when(실행): 어떠한 함수를 실행하면, then(검증): 어떠한 결과가 나와야 한다.
        assertThrows(CommonException.class, () -> memberValidator.assertMemberExist(member));
    }

    @Test
    public void 회원이_존재하지_않다면_예외가_발생하지_않는다() {
        // given(준비): 어떠한 데이터가 준비되었을 때
        Optional<Member> nullMember = Optional.ofNullable(null);

        // when(실행): 어떠한 함수를 실행하면, then(검증): 어떠한 결과가 나와야 한다.
        assertThatCode(() -> memberValidator.assertMemberExist(nullMember))
                .doesNotThrowAnyException();
    }
}