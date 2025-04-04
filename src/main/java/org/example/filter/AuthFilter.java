package org.example.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDateTime;

/**
 * 인증 처리를 담당하는 필터 클래스입니다.
 * 로그인 여부를 확인하여 보호된 리소스에 대한 접근을 제어합니다.
 */
public class AuthFilter implements Filter {

    /**
     * 로그인 없이 접근 가능한 경로 목록 (화이트리스트)
     */
    private static final String[] WHITE_LIST = {
            "/api/users",       // 회원가입
            "/api/users/login"  // 로그인
    };

    /**
     * 요청이 들어올 때마다 실행되는 메서드로, 로그인 여부를 검사합니다.
     *
     * @param request  서블릿 요청 객체
     * @param response 서블릿 응답 객체
     * @param chain    필터 체인
     * @throws IOException 예외 발생 시 처리
     * @throws ServletException 서블릿 처리 중 예외 발생 시 처리
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

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

        boolean hasUserId = false; // 로그인 여부 확인용
        Cookie[] cookies = httpRequest.getCookies();

        if (cookies != null) {
            for (Cookie cookie : cookies) {
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