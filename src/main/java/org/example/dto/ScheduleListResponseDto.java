package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.example.entity.Schedule;

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

    public static ScheduleListResponseDto from(Schedule schedule, int commentCount) {
        return new ScheduleListResponseDto(
                schedule.getTitle(),
                schedule.getContent(),
                commentCount,
                schedule.getCreatedAt(),
                schedule.getUpdatedAt(),
                schedule.getUser().getUsername()
        );
    }
}
