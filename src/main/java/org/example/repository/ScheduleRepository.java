package org.example.repository;

import org.example.entity.Schedule;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Pageable;

import java.util.List;

// ✅ Schedule 엔티티를 관리하는 JPA Repository 인터페이스
@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

    // ✅ 일정 전체를 최신순으로 조회
    @EntityGraph(attributePaths = "user")
    List<Schedule> findAllByOrderByCreatedAtDesc();

    // ✅ 페이징 처리된 일정 전체를 수정일 기준 내림차순으로 조회
    @EntityGraph(attributePaths = "user")
    Page<Schedule> findAllByOrderByUpdatedAtDesc(Pageable pageable);
}