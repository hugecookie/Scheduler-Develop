package org.example.dto;

import lombok.Getter;
import org.example.entity.User;

@Getter
public class LoginResponseDto {
    private Long userId;
    private String username;

    public static LoginResponseDto from(User user) {
        return new LoginResponseDto(user.getId(), user.getUsername());
    }

    public LoginResponseDto(Long userId, String username) {
        this.userId = userId;
        this.username = username;
    }
}
