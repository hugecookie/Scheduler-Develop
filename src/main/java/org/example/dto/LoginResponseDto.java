package org.example.dto;

import lombok.Getter;
import org.example.entity.User;

@Getter
public class LoginResponseDto {

    // ✅ 로그인한 유저의 ID
    private Long userId;

    // ✅ 로그인한 유저의 이름
    private String username;

    // ✅ Jwt 토큰
    private String token;

    // ✅ 생성자
    public LoginResponseDto(Long userId, String username, String token) {
        this.userId = userId;
        this.username = username;
        this.token = token;
    }

    // ✅ 엔티티 객체를 DTO로 변환하는 정적 메서드
    public static LoginResponseDto from(User user, String token) {
        return new LoginResponseDto(user.getId(), user.getUsername(), token);
    }
}