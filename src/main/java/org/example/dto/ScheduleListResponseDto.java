package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class ScheduleListResponseDto {
    private String title;
    private String content;
    private int commentCount;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String username;
}
