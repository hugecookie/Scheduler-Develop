package org.example.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

// ✅ 일정 생성/수정 시 클라이언트로부터 전달받는 데이터
@Getter
public class ScheduleRequestDto {

    // ✅ 할 일 제목 (필수)
    @NotBlank(message = "제목은 필수입니다.")
    private String title;

    // ✅ 할 일 내용 (선택)
    private String content;

    // ✅ 작성자 ID
    @NotNull
    private Long userId;
}