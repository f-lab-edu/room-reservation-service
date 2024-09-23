package com.ryan.roomreservationservice.application.port.in;

import com.ryan.roomreservationservice.application.port.in.command.MemberCommand;

public interface MemberUseCase {
    void onSignUp(MemberCommand.SignUpCommand command);
    String onSignIn(MemberCommand.SignInCommand command);
}
