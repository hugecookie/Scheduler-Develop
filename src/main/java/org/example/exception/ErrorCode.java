package org.example.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum ErrorCode {

    // ✅ 유저 로직 관련 에러
    USER_NOT_FOUND("C001", "존재하지 않는 사용자입니다", HttpStatus.UNAUTHORIZED),
    PASSWORD_MISMATCH("C002", "비밀번호가 일치하지 않습니다", HttpStatus.UNAUTHORIZED),
    DUPLICATED_EMAIL("C003", "이미 등록된 이메일입니다.", HttpStatus.CONFLICT),

    // ✅ 일정 관련 에러
    SCHEDULE_NOT_FOUND("S001", "존재하지 않는 일정입니다", HttpStatus.NOT_FOUND),

    // ✅ 댓글 관련 에러
    COMMENT_NOT_FOUND("CM001", "존재하지 않는 댓글입니다", HttpStatus.NOT_FOUND),
    UNAUTHORIZED("CM002", "해당 댓글에 대한 권한이 없습니다", HttpStatus.UNAUTHORIZED);

    private final String code;
    private final String message;
    private final HttpStatus status;
}
