package com.work.auth.service;

import com.work.auth.dto.AuthResponse;
import com.work.domain.user.entity.User;
import com.work.domain.user.mapper.UserMapper;
import com.work.global.jwt.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;

    public AuthResponse login(String email, String password) {

        // 1. 유저 조회
        User user = userMapper.findByEmail(email);
        if (user == null) {
            throw new RuntimeException("이메일 없음");
        }

        // 2. 비밀번호 검증
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new RuntimeException("비밀번호 틀림");
        }

        // 3. JWT 발급
        String token = jwtProvider.createToken(user.getId());

        // 4. 토큰 + 사번 반환
        return new AuthResponse(
                token,
                user.getEmail()
        );
    }
}
