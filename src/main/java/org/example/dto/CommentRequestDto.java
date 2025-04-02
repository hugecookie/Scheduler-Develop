package org.example.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class CommentRequestDto {

    @NotBlank
    private String content;

    @NotNull
    private Long scheduleId;

    @NotNull
    private Long userId;
}
