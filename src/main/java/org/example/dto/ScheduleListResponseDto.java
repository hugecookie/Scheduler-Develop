package org.example.dto;

import lombok.Getter;
import org.example.entity.Schedule;

import java.time.LocalDateTime;

@Getter
public class ScheduleListResponseDto {
    // ✅ 일정 제목
    private String title;

    // ✅ 일정 내용
    private String content;

    // ✅ 댓글 개수
    private int commentCount;

    // ✅ 작성 시간
    private LocalDateTime createdAt;

    // ✅ 수정 시간
    private LocalDateTime updatedAt;

    // ✅ 작성자 이름
    private String username;

    // ✅ 생성자
    public ScheduleListResponseDto(String title, String content, int commentCount,
                                   LocalDateTime createdAt, LocalDateTime updatedAt,
                                   String username) {
        this.title = title;
        this.content = content;
        this.commentCount = commentCount;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.username = username;
    }

    // ✅ Schedule 엔티티와 댓글 수를 기반으로 DTO 변환
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
