package org.example.dto;

import lombok.Getter;
import org.example.entity.User;

@Getter
public class LoginResponseDto {

    // ✅ 로그인한 유저의 ID
    private Long userId;

    // ✅ 로그인한 유저의 이름
    private String username;

    // ✅ userId와 username을 설정하는 생성자
    public LoginResponseDto(Long userId, String username) {
        this.userId = userId;
        this.username = username;
    }

    // ✅ 엔티티 객체를 DTO로 변환하는 정적 메서드
    public static LoginResponseDto from(User user) {
        return new LoginResponseDto(user.getId(), user.getUsername());
    }
}