package org.example.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

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

    // 추후 Lv2에서 작성자(User)와 연관관계 추가 예정
}
