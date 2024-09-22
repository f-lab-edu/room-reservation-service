package com.ryan.roomreservationservice.application.service.validator;

import com.ryan.roomreservationservice.domain.Member;
import com.ryan.roomreservationservice.utils.exception.CommonException;
import com.ryan.roomreservationservice.utils.exception.ErrorMessage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MemberValidatorTest {

    private PasswordEncoder passwordEncoder;
    private MemberValidator memberValidator;

    @BeforeEach
    public void setUp() {
        this.passwordEncoder = new BCryptPasswordEncoder();
        this.memberValidator = new MemberValidator(this.passwordEncoder);
    }

    @Test
    public void 회원이_존재한다고_검증() {
        // given(준비): 어떠한 데이터가 준비되었을 때
        Member member = Member.builder()
                .memberId(1L)
                .userId("ryan@gamil.com")
                .password("1234qweR!!")
                .name("ryan")
                .paymentHistories(List.of())
                .cards(List.of())
                .build();

        Optional<Member> optionalMember = Optional.ofNullable(member);

        // when(실행): 어떠한 함수를 실행하면
        CommonException exception = assertThrows(CommonException.class, () -> this.memberValidator.assertMemberExist(optionalMember));

        // then(검증): 어떠한 결과가 나와야 한다.
        assertThat(exception.getClientErrorMessage()).isEqualTo(ErrorMessage.EXIST_MEMBER);
    }

    @Test
    public void 회원이_존재하지_않다고_검증() {
        // given(준비): 어떠한 데이터가 준비되었을 때
        Optional<Member> optionalMember = Optional.empty();

        // when(실행): 어떠한 함수를 실행하면
        CommonException exception = assertThrows(CommonException.class, () -> this.memberValidator.assertMemberNotExist(optionalMember));

        // then(검증): 어떠한 결과가 나와야 한다.
        assertThat(exception.getClientErrorMessage()).isEqualTo(ErrorMessage.NOT_EXIST_MEMBER);
    }

    @Test
    void 올바른_비밀번호를_입력하지_않았을_때_검증() {
        // given(준비): 어떠한 데이터가 준비되었을 때
        String newPassword = "wrongPassword";
        String oldPassword = this.passwordEncoder.encode("1234qweR!!");

        // when(실행): 어떠한 함수를 실행하면
        CommonException exception = assertThrows(CommonException.class, () -> this.memberValidator.assertPasswordMissMatches(newPassword, oldPassword));

        // then(검증): 어떠한 결과가 나와야 한다.
        assertThat(exception.getClientErrorMessage()).isEqualTo(ErrorMessage.PASSWORD_NOT_MATCH);
    }

    @Test
    void 올바른_비밀번호를_입력햇을_때_검증() {
        // given(준비): 어떠한 데이터가 준비되었을 때
        String newPassword = "1234qweR!!";
        String oldPassword = this.passwordEncoder.encode("1234qweR!!");

        // when(실행): 어떠한 함수를 실행하면
        // then(검증): 어떠한 결과가 나와야 한다.
        assertDoesNotThrow(() -> memberValidator.assertPasswordMissMatches(newPassword, oldPassword));
    }

}