package com.ryan.roomreservationservice.service.member;

import com.ryan.roomreservationservice.config.jwt.TokenProvider;
import com.ryan.roomreservationservice.domain.Member;
import com.ryan.roomreservationservice.repository.member.MemberQueryImp;
import com.ryan.roomreservationservice.repository.member.MemberStoreImp;
import com.ryan.roomreservationservice.repository.validator.MemberValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ActiveProfiles("memory")
@ExtendWith(MockitoExtension.class)
class MemberServiceImpTest {

    private MemberServiceImp memberServiceImp;

    @Mock
    private MemberQueryImp memberQueryImp;

    @Mock
    private MemberStoreImp memberStoreImp;

    @Mock
    private MemberValidator memberValidator;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private TokenProvider tokenProvider;

    @BeforeEach
    void init() {
        this.memberServiceImp = new MemberServiceImp(
                memberQueryImp,
                memberStoreImp,
                memberValidator,
                passwordEncoder,
                tokenProvider
        );
    }

    @Test
    public void 회원가입() {
        // given(준비): 어떠한 데이터가 준비되었을 때
        MemberCommand.SignUpRequest signUpRequest = MemberCommand.SignUpRequest.builder()
                .email("ryan@gamil.com")
                .password("1234qweR!!")
                .name("ryan")
                .phone("01012345678")
                .build();

        Member member = Member.builder()
                .email(signUpRequest.getEmail())
                .password(signUpRequest.getPassword())
                .name(signUpRequest.getName())
                .phone(signUpRequest.getPhone())
                .build();

        Optional<Member> emptyMember = Optional.empty();

        String encryptionPassword = "encrypted" + signUpRequest.getPassword();

        when(this.memberQueryImp.findByEmail(signUpRequest.getEmail())).thenReturn(emptyMember);
        when(this.passwordEncoder.encode(signUpRequest.getPassword())).thenReturn(encryptionPassword);
        when(this.memberStoreImp.save(signUpRequest.getEmail(), encryptionPassword, signUpRequest.getName(), signUpRequest.getPhone())).thenReturn(member);

        // when(실행): 어떠한 함수를 실행하면
        memberServiceImp.onSignUp(signUpRequest);

        // then(검증): 어떠한 결과가 나와야 한다.
        verify(this.memberStoreImp, times(1))
                .save(signUpRequest.getEmail(),
                        encryptionPassword,
                        signUpRequest.getName(),
                        signUpRequest.getPhone());
    }

    @Test
    public void 로그인성공() {
        // given(준비): 어떠한 데이터가 준비되었을 때
        MemberCommand.SignInRequest signInRequest = MemberCommand.SignInRequest.builder()
                .email("ryan@gmail.com")
                .password("1234qweR!!")
                .build();

        Member member = Member.builder()
                .email("ryan@gamil.com")
                .password("1234qweR!!")
                .name("ryan")
                .phone("01012345678")
                .build();

        Optional<Member> optionalMember = Optional.ofNullable(member);
        String exampleToken = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6ImNsdHYwZ244OTAwMDBlenZkdzdxZzZubGIiLCJpYXQiOjE3MTExNzI4OTQsImV4cCI6MTcxMTE3NjQ5NH0.tPqDlyWOyRtOYRgN2mD5lfpea-v3PzTgV8Eqs7E4V-U";

        when(this.memberQueryImp.findByEmail(signInRequest.getEmail()))
                .thenReturn(optionalMember);
        when(this.memberValidator.assertMemberNotExist(optionalMember))
                .thenReturn(member);
        when(this.tokenProvider.createToken(String.format("%s:%s", member.getEmail(), member.getName())))
                .thenReturn(exampleToken);

        // when(실행): 어떠한 함수를 실행하면
        String token = this.memberServiceImp.onSignIn(signInRequest);

        // then(검증): 어떠한 결과가 나와야 한다.
        assertThat(token).isEqualTo(exampleToken);
    }

}