package org.example.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class CommentRequestDto {

    // ✅ 댓글 내용
    @NotBlank
    private String content;

    // ✅ 작성자 유저 ID
    @NotNull
    private Long scheduleId;

    // ✅ 연관된 일정 ID
    @NotNull
    private Long userId;
}
