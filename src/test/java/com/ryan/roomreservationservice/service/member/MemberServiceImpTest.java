package com.ryan.roomreservationservice.service.member;

import com.ryan.roomreservationservice.domain.Member;
import com.ryan.roomreservationservice.repository.member.MemberQueryImp;
import com.ryan.roomreservationservice.repository.member.MemberStoreImp;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.mockito.Mockito.*;

@SpringBootTest
@ActiveProfiles("memory")
@ExtendWith(MockitoExtension.class)
class MemberServiceImpTest {

    @Autowired
    private MemberServiceImp memberServiceImp;

    @MockBean
    private MemberQueryImp memberQueryImp;

    @MockBean
    private MemberStoreImp memberStoreImp;

    @MockBean
    private PasswordEncoder passwordEncoder;

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

        Optional<Member> optionalMember = Optional.empty();

        when(this.memberQueryImp.findByEmail(signUpRequest.getEmail())).thenReturn(optionalMember);
        when(this.memberStoreImp.save(
                signUpRequest.getEmail(), this.passwordEncoder.encode(signUpRequest.getPassword()),
                signUpRequest.getName(), signUpRequest.getPhone())
        ).thenReturn(member);

        // when(실행): 어떠한 함수를 실행하면
        memberServiceImp.onSignUp(signUpRequest);

        // then(검증): 어떠한 결과가 나와야 한다.
        verify(this.memberStoreImp, times(1))
                .save(signUpRequest.getEmail(),
                        this.passwordEncoder.encode(signUpRequest.getPassword()),
                        signUpRequest.getName(),
                        signUpRequest.getPhone());
    }
}