package com.work.domain.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.work.domain.user.mapper.UserMapper;
import com.work.domain.user.entity.User;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    public void register(User user) {

        // 중복 사원번호 검사
        if (userMapper.findByEmail(user.getEmail()) != null) {
            throw new RuntimeException("이미 가입된 사원번호 입니다.");
        }

        // ⭐ 비밀번호 암호화 SecurityConfig bean이 여기걸 불러서 암호화 시키는 거임
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        userMapper.insertUser(user);
    }
}