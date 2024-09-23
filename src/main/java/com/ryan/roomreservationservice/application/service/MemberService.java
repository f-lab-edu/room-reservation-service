package com.ryan.roomreservationservice.application.service;

import com.ryan.roomreservationservice.application.port.in.MemberUseCase;
import com.ryan.roomreservationservice.application.port.in.command.MemberCommand;
import com.ryan.roomreservationservice.application.port.out.CommandMemberPort;
import com.ryan.roomreservationservice.application.port.out.QueryMemberPort;
import com.ryan.roomreservationservice.application.service.mapper.MemberServiceMapper;
import com.ryan.roomreservationservice.application.service.validator.MemberValidator;
import com.ryan.roomreservationservice.config.jwt.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService implements MemberUseCase {
    private final MemberServiceMapper mapper;
    private final QueryMemberPort queryMemberPort;
    private final CommandMemberPort commandMemberPort;
    private final MemberValidator memberValidator;
    private final TokenProvider tokenProvider;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void onSignUp(MemberCommand.SignUpCommand command) {
        var userId = command.getUserId();
        var password = this.passwordEncoder.encode(command.getPassword());
        var name = command.getName();

        var memberOptional = this.queryMemberPort.findOneByUserId(userId);
        this.memberValidator.assertMemberExist(memberOptional);
        this.commandMemberPort.save(this.mapper.mapToMember(userId, password, name));
    }

    @Override
    public String onSignIn(MemberCommand.SignInCommand command) {
        var userId = command.getUserId();
        var password = command.getPassword();

        var memberOptional = this.queryMemberPort.findOneByUserId(userId);
        var member = this.memberValidator.assertMemberNotExist(memberOptional);
        this.memberValidator.assertPasswordMissMatches(password, member.getPassword());

        return this.tokenProvider.createToken(String.format("%s:%s", member.getUserId(), member.getName()));
    }
}
