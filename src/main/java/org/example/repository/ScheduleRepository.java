package org.example.repository;

import org.example.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

// ✅ Schedule 엔티티를 관리하는 JPA Repository 인터페이스
@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

    // 일정 전체를 최신순으로 조회
    List<Schedule> findAllByOrderByCreatedAtDesc();
}