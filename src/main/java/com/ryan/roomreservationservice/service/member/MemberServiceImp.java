package com.ryan.roomreservationservice.service.member;

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
}
