package com.ryan.roomreservationservice.config;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.assertj.core.api.Assertions.assertThat;

public class PasswordEncoderTest {

    @Test
    public void 비밀번호_암호화_검증() {
        // given(준비): 어떠한 데이터가 준비되었을 때
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String password = "1234qweR!!";

        // when(실행): 어떠한 함수를 실행하면
        String encryptionPassword = passwordEncoder.encode(password);
        boolean matchesPassword = passwordEncoder.matches(password, encryptionPassword);

        // then(검증): 어떠한 결과가 나와야 한다.
        assertThat(matchesPassword).isTrue();
    }
}
