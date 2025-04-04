package org.example.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.dto.CommentRequestDto;
import org.example.dto.CommentResponseDto;
import org.example.service.CommentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * ✅ 댓글 관련 API 요청을 처리하는 컨트롤러입니다.
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/comments")
public class CommentController {

    private final CommentService commentService;

    /**
     * ✅ 댓글을 생성합니다.
     *
     * @param requestDto 댓글 요청 데이터
     * @return 생성된 댓글 응답 DTO
     */
    @PostMapping
    public ResponseEntity<CommentResponseDto> createComment(@RequestBody @Valid CommentRequestDto requestDto) {
        return ResponseEntity.ok(commentService.createComment(requestDto));
    }

    /**
     * ✅ 특정 일정의 댓글 목록을 조회합니다.
     *
     * @param scheduleId 일정 ID
     * @return 댓글 응답 DTO 리스트
     */
    @GetMapping("/{scheduleId}")
    public ResponseEntity<List<CommentResponseDto>> getComments(@PathVariable Long scheduleId) {
        return ResponseEntity.ok(commentService.getComments(scheduleId));
    }

    /**
     * ✅ 댓글을 수정합니다.
     *
     * @param id 댓글 ID
     * @param requestDto 수정할 댓글 데이터
     * @return 수정된 댓글 응답 DTO
     */
    @PutMapping("/{id}")
    public ResponseEntity<CommentResponseDto> updateComment(@PathVariable Long id,
                                                            @RequestBody @Valid CommentRequestDto requestDto) {
        return ResponseEntity.ok(commentService.updateComment(id, requestDto));
    }

    /**
     * ✅ 댓글을 삭제합니다.
     *
     * @param id 댓글 ID
     * @param userId 사용자 ID (요청자)
     * @return 빈 응답
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long id,
                                              @RequestParam Long userId) {
        commentService.deleteComment(id, userId);
        return ResponseEntity.ok().build();
    }
}
