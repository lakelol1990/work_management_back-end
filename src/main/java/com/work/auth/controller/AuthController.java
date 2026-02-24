package com.work.auth.controller;

import com.work.auth.dto.AuthResponse;
import com.work.auth.dto.LoginRequest;
import com.work.auth.service.AuthService;
import com.work.domain.user.entity.User;
import com.work.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;
    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {

        try {
            System.out.println("회원가입 시도: " + user.getEmail());

            userService.register(user);

            System.out.println("회원가입 성공");

            return ResponseEntity.ok().build();

        } catch (Exception e) {
            e.printStackTrace(); // ★ 진짜 에러 원인 출력
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    /*@PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {

        String token = authService.login(request.getEmail(), request.getPassword());

        return ResponseEntity.ok(new AuthResponse(token));
    }*/

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {

        AuthResponse response = authService.login(request.getEmail(), request.getPassword());

        return ResponseEntity.ok(response);
    }
}