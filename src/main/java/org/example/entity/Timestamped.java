package org.example.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class Timestamped {

    // ✅ 생성된 시간
    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdAt;

    // ✅ 업데이트된 시간
    @LastModifiedDate
    private LocalDateTime updatedAt;
}

