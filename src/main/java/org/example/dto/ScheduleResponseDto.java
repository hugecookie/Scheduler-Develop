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

    // 생성자: 엔티티를 DTO로 변환
    public ScheduleResponseDto(Schedule schedule) {
        this.id = schedule.getId();
        this.title = schedule.getTitle();
        this.content = schedule.getContent();
    }
}