package org.example.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDateTime;

// ✅ 인증 처리를 담당하는 필터 클래스
public class AuthFilter implements Filter {

    // ✅ 로그인 및 회원가입은 인증 없이 접근 가능한 경로(화이트리스트)
    private static final String[] WHITE_LIST = {
            "/api/users",       // 회원가입
            "/api/users/login"  // 로그인
    };

    // ✅ 요청이 들어올 때마다 실행되는 필터 메서드
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        // ✅ HttpServletRequest, HttpServletResponse로 다운 캐스팅
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        // ✅ 요청 URI 확인
        String uri = httpRequest.getRequestURI();

        // 회원가입만 허용 (POST /api/users)
        if (uri.equals("/api/users") && httpRequest.getMethod().equals("POST")) {
            chain.doFilter(request, response);
            return;
        }

        // 로그인 허용
        if (uri.equals("/api/users/login")) {
            chain.doFilter(request, response);
            return;
        }


        // ✅ 로그인 여부 확인 (쿠키 기반)
        boolean hasUserId = false; // userId 쿠키 존재 여부

        // ✅ 요청에 포함된 모든 쿠키 배열 가져오기
        Cookie[] cookies = httpRequest.getCookies();

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                // ✅ userId 쿠키가 있으면 로그인된 사용자로 간주
                if ("userId".equals(cookie.getName())) {
                    hasUserId = true;
                    break;
                }
            }
        }

        // ✅ 로그인되지 않은 사용자일 경우 응답 차단
        if (!hasUserId) {
            httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED); // 401

            // ✅ 응답 JSON 및 인코딩 설정
            httpResponse.setCharacterEncoding("UTF-8");
            httpResponse.setContentType("application/json");

            String json = """
            {
                "timestamp": "%s",
                "status": 401,
                "error": "UNAUTHORIZED",
                "code": "A001",
                "message": "로그인이 필요합니다",
                "path": "%s"
            }
            """.formatted(LocalDateTime.now(), uri);

            httpResponse.getWriter().write(json);
            return;
        }


        // ✅ 로그인된 사용자면 다음 필터 또는 컨트롤러로 요청 전달
        chain.doFilter(request, response);
    }
}
