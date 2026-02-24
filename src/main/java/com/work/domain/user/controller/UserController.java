package com.work.domain.user.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

    // 로그인한 사용자 정보 확인용 API
    @GetMapping("/api/test/me")
    public String me(Authentication authentication) {

        Long userId = (Long) authentication.getPrincipal();

        return "login user id = " + userId;
    }
}