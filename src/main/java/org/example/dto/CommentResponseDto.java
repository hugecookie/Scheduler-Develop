package org.example.dto;

import lombok.Getter;
import org.example.entity.Comment;

import java.time.LocalDateTime;

@Getter
public class CommentResponseDto {
    private Long id;
    private String content;
    private String username;
    private Long scheduleId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public CommentResponseDto(Long id, String content, String username, Long scheduleId, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.content = content;
        this.username = username;
        this.scheduleId = scheduleId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

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
