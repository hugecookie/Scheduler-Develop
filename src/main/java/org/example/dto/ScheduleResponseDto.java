package org.example.dto;

import lombok.Getter;
import org.example.entity.Schedule;

// ✅ 일정 조회 시 클라이언트에 응답으로 보내는 데이터
@Getter
public class ScheduleResponseDto {

    // 일정 ID
    private Long id;

    // 제목
    private String title;

    // 내용
    private String content;

    // 작성자 Id
    private Long userId;

    // 작성자 이름
    private String username;

    // 작성자 email
    private String email;

    public ScheduleResponseDto(Long id, String title, String content,
                               Long userId, String username, String email) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.userId = userId;
        this.username = username;
        this.email = email;
    }

    public static ScheduleResponseDto from(Schedule schedule) {
        return new ScheduleResponseDto(
                schedule.getId(),
                schedule.getTitle(),
                schedule.getContent(),
                schedule.getUser().getId(),
                schedule.getUser().getUsername(),
                schedule.getUser().getEmail()
        );
    }
}