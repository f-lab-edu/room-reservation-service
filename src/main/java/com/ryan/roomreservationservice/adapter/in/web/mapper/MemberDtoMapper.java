package com.ryan.roomreservationservice.adapter.in.web.mapper;

import com.ryan.roomreservationservice.adapter.in.web.dto.MemberDto;
import com.ryan.roomreservationservice.application.port.in.command.MemberCommand;
import org.springframework.stereotype.Component;

@Component
public class MemberDtoMapper {
    public MemberCommand.SignUpCommand mapToSignUpCommand(MemberDto.SignUpRequest request) {
        return MemberCommand.SignUpCommand.builder()
                .userId(request.getUserId())
                .password(request.getPassword())
                .name(request.getName())
                .build();
    }

    public MemberCommand.SignInCommand mapToSignInCommand(MemberDto.SignInRequest request) {
        return MemberCommand.SignInCommand.builder()
                .userId(request.getUserId())
                .password(request.getPassword())
                .build();
    }

    public MemberDto.SignInResponse mapToSignInResponse(String accessToken) {
        return MemberDto.SignInResponse.builder()
                .accessToken(accessToken)
                .build();
    }
}
