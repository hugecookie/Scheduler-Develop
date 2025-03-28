package org.example.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class User extends Timestamped {

    // ✅ 유저 고유 ID (PK)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // ✅ 유저명 (필수 입력)
    @Column(nullable = false, length = 20)
    private String username;

    // ✅ 이메일 (필수 입력)
    @Column(nullable = false, length = 20)
    private String email;

    // ✅ 비밀번호 (필수 입력)
    @Column(nullable = false, length = 20)
    private String password;

    // ✅ 생성자 (회원 가입 시 사용)
    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    // ✅ 수정 메서드 (이메일, 비밀번호 수정)
    public void update(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
