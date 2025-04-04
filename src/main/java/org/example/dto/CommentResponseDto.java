package org.example.dto;

import lombok.Getter;
import org.example.entity.Comment;

import java.time.LocalDateTime;

@Getter
public class CommentResponseDto {

    // ✅ 댓글 ID
    private Long id;

    // ✅ 댓글 내용
    private String content;

    // ✅ 작성자 이름
    private String username;

    // ✅ 연관된 일정 ID
    private Long scheduleId;

    // ✅ 댓글 수정 시간
    private LocalDateTime createdAt;

    // ✅ 댓글 생성 시간
    private LocalDateTime updatedAt;

    // ✅ Comment 엔티티로부터 응답 DTO 생성
    public CommentResponseDto(Long id, String content, String username, Long scheduleId, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.content = content;
        this.username = username;
        this.scheduleId = scheduleId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    // ✅ Comment 엔티티를 CommentResponseDto로 변환하는 정적 메서드
    public static CommentResponseDto from(Comment comment) {
        return new CommentResponseDto(
                comment.getId(),
                comment.getContent(),
                comment.getUser().getUsername(),
                comment.getSchedule().getId(),
                comment.getCreatedAt(),
                comment.getUpdatedAt()
        );
    }
}
