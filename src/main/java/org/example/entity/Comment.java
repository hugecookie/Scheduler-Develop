package org.example.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Comment extends Timestamped {

    // ✅ 댓글 고유 ID (PK)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // ✅ 댓글 내용 (최대 200자)
    @Column(nullable = false, length = 200)
    private String content;

    // ✅ 연관관계: 작성자 (User) - user_id FK
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    // ✅ 연관관계: 일정 (Schedule) - schedule_id FK
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "schedule_id", nullable = false)
    private Schedule schedule;

    // ✅ 생성자 (content, user, schedule)
    public Comment(String content, User user, Schedule schedule) {
        this.content = content;
        this.user = user;
        this.schedule = schedule;
    }

    // ✅ 수정 메서드
    public void update(String content) {
        this.content = content;
    }
}