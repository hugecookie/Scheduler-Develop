package org.example.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum ErrorCode {

    // ✅ 로그인 관련 에러
    USER_NOT_FOUND("C001", "존재하지 않는 사용자입니다", HttpStatus.UNAUTHORIZED),
    PASSWORD_MISMATCH("C002", "비밀번호가 일치하지 않습니다", HttpStatus.UNAUTHORIZED),

    // ✅ 일정 관련 에러
    SCHEDULE_NOT_FOUND("S001", "존재하지 않는 일정입니다", HttpStatus.NOT_FOUND);

    private final String code;
    private final String message;
    private final HttpStatus status;
}
