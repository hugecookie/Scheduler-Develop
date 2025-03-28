package org.example.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserRequestDto {

    // ✅ 유저명 (필수)
    @NotBlank(message = "유저명은 필수입니다.")
    private String username;

    // ✅ 이메일 (필수, 형식 검증)
    @NotBlank(message = "이메일은 필수입니다.")
    @Email(message = "이메일 형식이 올바르지 않습니다.")
    private String email;

    // ✅ 비밀번호 (필수)
    @NotBlank(message = "비밀번호는 필수입니다.")
    private String password;
}
