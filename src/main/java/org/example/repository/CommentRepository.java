package org.example.repository;

import org.example.entity.Comment;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    // ✅ 일정 ID 기준으로 댓글 목록 조회 (최신순 정렬)
    @EntityGraph(attributePaths = "user")
    List<Comment> findAllByScheduleIdOrderByCreatedAtDesc(Long scheduleId);

    // ✅ 댓글 개수를 반환
    int countByScheduleId(Long scheduleId);
}