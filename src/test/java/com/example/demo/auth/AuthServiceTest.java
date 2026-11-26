package com.example.demo.auth;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class AuthServiceTest {

    private final AuthService authService = new AuthService();

    @Test
    void 가입한_사용자는_로그인할_수_있다() {
        authService.signup("new@moa.dev", "pw1234", "새유저");

        assertThat(authService.login("new@moa.dev", "pw1234").nickname()).isEqualTo("새유저");
    }

    @Test
    void 비밀번호가_틀리면_로그인에_실패한다() {
        assertThatThrownBy(() -> authService.login("demo@moa.dev", "wrong"))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 같은_이메일로_두_번_가입할_수_없다() {
        assertThatThrownBy(() -> authService.signup("demo@moa.dev", "pw", "중복"))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
