package org.example.filter;

import io.jsonwebtoken.Claims;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.example.jwt.JwtUtil;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDateTime;

@RequiredArgsConstructor
@Component
public class JwtAuthFilter implements Filter {

    private final JwtUtil jwtUtil;

    // ✅ 인증이 필요 없는 경로
    private static final String[] WHITE_LIST = {
            "/api/users",       // 회원가입
            "/api/users/login"  // 로그인
    };

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        String uri = httpRequest.getRequestURI();

        for (String path : WHITE_LIST) {
            if (uri.equals(path)) {
                chain.doFilter(request, response);
                return;
            }
        }

        // ✅ Authorization 헤더에서 토큰 추출
        String authHeader = httpRequest.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            reject(httpResponse, uri);
            return;
        }

        String token = authHeader.substring(7); // "Bearer " 제거

        if (!jwtUtil.isValidToken(token)) {
            reject(httpResponse, uri);
            return;
        }

        // ✅ 통과: 사용자 정보 추출 가능
        Claims claims = jwtUtil.getClaims(token);
        Long userId = jwtUtil.getUserId(token);
        request.setAttribute("userId", userId); // ✅ 사용자 ID를 request에 저장

        chain.doFilter(request, response);
    }

    private void reject(HttpServletResponse response, String path) throws IOException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");

        String json = """
        {
            \"timestamp\": \"%s\",
            \"status\": 401,
            \"error\": \"UNAUTHORIZED\",
            \"code\": \"A001\",
            \"message\": \"JWT 인증이 필요합니다\",
            \"path\": \"%s\"
        }
        """.formatted(LocalDateTime.now(), path);

        response.getWriter().write(json);
    }
}
