-- 처음 DB 생성 설정
# CREATE DATABASE schedule_develop
#     DEFAULT CHARACTER SET utf8mb4
#     COLLATE utf8mb4_unicode_ci;

USE schedule_develop;

-- 유저 테이블
CREATE TABLE users (
                       id BIGINT AUTO_INCREMENT PRIMARY KEY,
                       username VARCHAR(50) NOT NULL,
                       email VARCHAR(100) NOT NULL,
                       password VARCHAR(100) NOT NULL,
                       created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
                       updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- 일정 테이블
CREATE TABLE schedules (
                           id BIGINT AUTO_INCREMENT PRIMARY KEY,
                           title VARCHAR(255) NOT NULL,
                           content TEXT,
                           user_id BIGINT NOT NULL,
                           created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
                           updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                           CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES users(id)
);

-- 일정 변경 내역 테이블
CREATE TABLE schedules_history (
                                   id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                   schedule_id BIGINT NOT NULL,
                                   title VARCHAR(255),
                                   content TEXT,
                                   modified_at DATETIME DEFAULT CURRENT_TIMESTAMP,
                                   CONSTRAINT fk_schedule FOREIGN KEY (schedule_id) REFERENCES schedules(id)
);
