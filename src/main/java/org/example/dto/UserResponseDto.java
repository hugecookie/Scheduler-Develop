package org.example.dto;

import lombok.Getter;
import org.example.entity.User;

import java.time.LocalDateTime;

@Getter
public class UserResponseDto {

    // ✅ 유저 ID
    private Long id;

    // ✅ 유저 이름
    private String username;

    // ✅ 유저 이메일
    private String email;

    // ✅ 생성된 시간
    private LocalDateTime createdAt;

    // ✅ 수정된 마지막 시간
    private LocalDateTime updatedAt;

    // ✅ 생성자
    public UserResponseDto(Long id, String username, String email, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    // ✅ User 엔티티를 DTO로 변환
    public static UserResponseDto from(User user) {
        return new UserResponseDto(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getCreatedAt(),
                user.getUpdatedAt()
        );
    }
}
