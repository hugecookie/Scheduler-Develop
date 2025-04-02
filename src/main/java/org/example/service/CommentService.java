package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.dto.CommentRequestDto;
import org.example.dto.CommentResponseDto;
import org.example.entity.Comment;
import org.example.entity.Schedule;
import org.example.entity.User;
import org.example.exception.CustomException;
import org.example.exception.ErrorCode;
import org.example.repository.CommentRepository;
import org.example.repository.ScheduleRepository;
import org.example.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final ScheduleRepository scheduleRepository;

    // ✅ 댓글 작성
    public CommentResponseDto createComment(CommentRequestDto requestDto) {
        User user = userRepository.findById(requestDto.getUserId())
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));

        Schedule schedule = scheduleRepository.findById(requestDto.getScheduleId())
                .orElseThrow(() -> new CustomException(ErrorCode.SCHEDULE_NOT_FOUND));

        Comment comment = new Comment(requestDto.getContent(), user, schedule);
        commentRepository.save(comment);
        return new CommentResponseDto(comment);
    }

    // ✅ 댓글 목록 조회 (일정 기준)
    @Transactional(readOnly = true)
    public List<CommentResponseDto> getComments(Long scheduleId) {
        return commentRepository.findAllByScheduleIdOrderByCreatedAtDesc(scheduleId)
                .stream()
                .map(CommentResponseDto::new)
                .collect(Collectors.toList());
    }

    // ✅ 댓글 수정
    public CommentResponseDto updateComment(Long commentId, CommentRequestDto requestDto) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new CustomException(ErrorCode.COMMENT_NOT_FOUND));

        if (!comment.getUser().getId().equals(requestDto.getUserId())) {
            throw new CustomException(ErrorCode.UNAUTHORIZED);
        }

        comment.update(requestDto.getContent());
        return new CommentResponseDto(comment);
    }

    // ✅ 댓글 삭제
    public void deleteComment(Long commentId, Long userId) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new CustomException(ErrorCode.COMMENT_NOT_FOUND));

        if (!comment.getUser().getId().equals(userId)) {
            throw new CustomException(ErrorCode.UNAUTHORIZED);
        }

        commentRepository.delete(comment);
    }
}
