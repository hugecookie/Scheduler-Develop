package org.example.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.dto.CommentRequestDto;
import org.example.dto.CommentResponseDto;
import org.example.service.CommentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/comments")
public class CommentController {

    private final CommentService commentService;

    // ✅ 댓글 작성
    @PostMapping
    public ResponseEntity<CommentResponseDto> createComment(@RequestBody @Valid CommentRequestDto requestDto) {
        return ResponseEntity.ok(commentService.createComment(requestDto));
    }

    // ✅ 댓글 목록 조회
    @GetMapping("/{scheduleId}")
    public ResponseEntity<List<CommentResponseDto>> getComments(@PathVariable Long scheduleId) {
        return ResponseEntity.ok(commentService.getComments(scheduleId));
    }

    // ✅ 댓글 수정
    @PutMapping("/{id}")
    public ResponseEntity<CommentResponseDto> updateComment(@PathVariable Long id,
                                                            @RequestBody @Valid CommentRequestDto requestDto) {
        return ResponseEntity.ok(commentService.updateComment(id, requestDto));
    }

    // ✅ 댓글 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long id,
                                              @RequestParam Long userId) {
        commentService.deleteComment(id, userId);
        return ResponseEntity.ok().build();
    }
}
