package org.example.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.dto.ScheduleRequestDto;
import org.example.dto.ScheduleResponseDto;

@Entity
@Getter
@NoArgsConstructor
public class Schedule extends Timestamped {

    // ✅ 일정 고유 ID (PK)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // ✅ 할 일 제목 (필수 입력)
    @Column(nullable = false)
    private String title;

    // ✅ 할 일 내용 (선택 입력)
    private String content;

    // 생성자
    public Schedule(ScheduleRequestDto dto) {
        this.title = dto.getTitle();
        this.content = dto.getContent();
    }

    // 수정 메서드
    public void update(ScheduleRequestDto dto) {
        this.title = dto.getTitle();
        this.content = dto.getContent();
    }

    // 추후 Lv2에서 작성자(User)와 연관관계 추가 예정
}