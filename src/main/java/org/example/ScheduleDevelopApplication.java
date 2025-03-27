package org.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class ScheduleDevelopApplication {
    public static void main(String[] args) {

        // ✅ 메인 실행 메서드
        SpringApplication.run(ScheduleDevelopApplication.class, args);
    }
}