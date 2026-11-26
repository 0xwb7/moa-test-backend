package com.example.demo.auth;

import com.example.demo.auth.AuthController.LoginResponse;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class AuthService {

    // 데모용 메모리 저장소 (email -> 사용자)
    private final Map<String, User> users = new ConcurrentHashMap<>(Map.of(
            "demo@moa.dev", new User("demo@moa.dev", "password123", "데모유저")
    ));

    public LoginResponse login(String email, String password) {
        User user = users.get(email);
        if (user == null || !user.password().equals(password)) {
            throw new IllegalArgumentException("이메일 또는 비밀번호가 올바르지 않아요.");
        }
        return new LoginResponse("token-" + UUID.randomUUID(), user.nickname());
    }

    public void signup(String email, String password, String nickname) {
        if (users.putIfAbsent(email, new User(email, password, nickname)) != null) {
            throw new IllegalArgumentException("이미 가입된 이메일이에요.");
        }
    }

    record User(String email, String password, String nickname) {
    }
}
