package com.ryan.roomreservationservice.service.member;

import com.ryan.roomreservationservice.config.jwt.TokenProvider;
import com.ryan.roomreservationservice.domain.Member;
import com.ryan.roomreservationservice.repository.validator.MemberValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberServiceImp implements MemberService{

    private final MemberQuery memberQuery;
    private final MemberStore memberStore;
    private final MemberValidator memberValidator;
    private final PasswordEncoder passwordEncoder;
    private final TokenProvider tokenProvider;

    @Override
    public void onSignUp(MemberCommand.SignUpRequest signUpRequest) {
        var memberEntity = this.memberQuery.findByEmail(signUpRequest.getEmail());
        this.memberValidator.assertMemberExist(memberEntity);
        this.memberStore.save(
                signUpRequest.getEmail(),
                this.passwordEncoder.encode(signUpRequest.getPassword()),
                signUpRequest.getName(),
                signUpRequest.getPhone()
        );
    }

    @Override
    public String onSignIn(MemberCommand.SignInRequest signInRequest) {
        var memberEntity = this.memberQuery.findByEmail(signInRequest.getEmail());
        var member = this.memberValidator.assertMemberNotExist(memberEntity);
        this.memberValidator.assertPasswordMissMatches(signInRequest.getPassword(), member.getPassword());
        return this.tokenProvider.createToken(String.format("%s:%s", member.getEmail(), member.getName()));
    }
}
