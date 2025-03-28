package org.example.dto;

import lombok.Getter;
import org.example.entity.User;

import java.time.LocalDateTime;

@Getter
public class UserResponseDto {

    // ✅ 유저 고유 ID
    private Long id;

    // ✅ 유저명
    private String username;

    // ✅ 이메일
    private String email;

    // ✅ 생성일
    private LocalDateTime createdAt;

    // ✅ 수정일
    private LocalDateTime updatedAt;

    // ✅ Entity → DTO 변환 생성자
    public UserResponseDto(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.createdAt = user.getCreatedAt();
        this.updatedAt = user.getUpdatedAt();
    }
}
