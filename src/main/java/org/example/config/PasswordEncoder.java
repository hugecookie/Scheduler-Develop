package org.example.config;

import at.favre.lib.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Component;

@Component
public class PasswordEncoder {

    // ✅ 비밀번호 암호화
    public String encode(String rawPassword) {
        return BCrypt.withDefaults()
                .hashToString(BCrypt.MIN_COST, rawPassword.toCharArray());
    }

    // ✅ 암호화된 비밀번호와 입력된 비밀번호의 해시 비교
    public boolean matches(String rawPassword, String encodedPassword) {
        BCrypt.Result result = BCrypt.verifyer()
                .verify(rawPassword.toCharArray(), encodedPassword);
        return result.verified;
    }
}
