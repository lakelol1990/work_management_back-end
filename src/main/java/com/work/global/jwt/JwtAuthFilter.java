package com.work.global.jwt;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {

    private final JwtProvider jwtProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        String path = request.getRequestURI();

        // 🔥 로그인/회원가입은 인증 처리 자체를 하지 않음
        if (path.startsWith("/api/auth")) {
            filterChain.doFilter(request, response);
            return;
        }

        String token = resolveToken(request);

        // 토큰이 있을 때만 인증 시도
        if (token != null) {
            try {
                if (jwtProvider.validateToken(token)) {

                    Long userId = jwtProvider.getUserId(token);

                    UserDetails userDetails = org.springframework.security.core.userdetails.User
                            .withUsername(String.valueOf(userId))
                            .password("") // 사용 안함
                            .authorities("ROLE_USER")
                            .build();

                    UsernamePasswordAuthenticationToken authentication =
                            new UsernamePasswordAuthenticationToken(
                                    userDetails,
                                    null,
                                    userDetails.getAuthorities()
                            );

                    SecurityContextHolder.getContext().setAuthentication(authentication);

                    System.out.println("JWT 인증 성공 userId = " + userId);
                }
            } catch (Exception e) {
                // ❗ 중요: 실패해도 막지 않음 (비로그인 처리)
                System.out.println("JWT 파싱 실패 → 비로그인 처리");
            }
        } else {
            System.out.println("JWT 없음 → 비로그인 요청");
        }

        filterChain.doFilter(request, response);
    }

    private String resolveToken(HttpServletRequest request) {
        String bearer = request.getHeader("Authorization");

        if (StringUtils.hasText(bearer) && bearer.startsWith("Bearer ")) {
            return bearer.substring(7);
        }
        return null;
    }
}