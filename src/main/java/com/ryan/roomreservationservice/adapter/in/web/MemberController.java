package com.ryan.roomreservationservice.adapter.in.web;

import com.ryan.roomreservationservice.adapter.in.web.dto.MemberDto;
import com.ryan.roomreservationservice.adapter.in.web.mapper.MemberDtoMapper;
import com.ryan.roomreservationservice.application.port.in.MemberUseCase;
import com.ryan.roomreservationservice.utils.response.SuccessResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/member")
@Tag(name = "Member", description = "회원 인증 관련 API")
public class MemberController {
    private final MemberDtoMapper memberDtoMapper;
    private final MemberUseCase memberUseCase;

    @PostMapping("/sign-up")
    @Operation(summary = "회원가입", description = "신규 유저 회원가입 기능을 제공합니다.")
    public SuccessResponse<Void> onSignUp(@RequestBody @Valid MemberDto.SignUpRequest request) {
        var memberCommand = this.memberDtoMapper.mapToSignUpCommand(request);
        this.memberUseCase.onSignUp(memberCommand);
        return SuccessResponse.setDefaultResponse();
    }

    @PostMapping("/sign-in")
    @Operation(summary = "로그인", description = "신규가입을 성공한 유저는 아이디와 비밀번호를 통해 인증 토큰을 발급 받습니다.")
    public SuccessResponse<MemberDto.SignInResponse> onSignIn(@RequestBody @Valid MemberDto.SignInRequest request) {
        var memberCommand = this.memberDtoMapper.mapToSignInCommand(request);
        var token = this.memberUseCase.onSignIn(memberCommand);
        var data = this.memberDtoMapper.mapToSignInResponse(token);
        return SuccessResponse.setSuccessResponse(data);
    }

}
