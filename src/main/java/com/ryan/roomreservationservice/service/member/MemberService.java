package com.ryan.roomreservationservice.service.member;

public interface MemberService {
    void onSignUp(MemberCommand.SignUpRequest signUpRequest);
    String onSignIn(MemberCommand.SignInRequest signInRequest);
}
