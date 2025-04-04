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

/**
 * ✅ 댓글 관련 비즈니스 로직을 처리하는 서비스 클래스입니다.
 */
@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final ScheduleRepository scheduleRepository;

    /**
     * ✅ 댓글을 생성합니다.
     *
     * @param requestDto 댓글 생성 요청 데이터
     * @return 생성된 댓글 응답 DTO
     */
    public CommentResponseDto createComment(CommentRequestDto requestDto) {
        User user = userRepository.findById(requestDto.getUserId())
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));

        Schedule schedule = scheduleRepository.findById(requestDto.getScheduleId())
                .orElseThrow(() -> new CustomException(ErrorCode.SCHEDULE_NOT_FOUND));

        Comment comment = new Comment(requestDto.getContent(), user, schedule);
        commentRepository.save(comment);
        return CommentResponseDto.from(comment);
    }

    /**
     * ✅ 일정에 해당하는 댓글 목록을 조회합니다.
     *
     * @param scheduleId 일정 ID
     * @return 댓글 응답 DTO 리스트
     */
    @Transactional(readOnly = true)
    public List<CommentResponseDto> getComments(Long scheduleId) {
        return commentRepository.findAllByScheduleIdOrderByCreatedAtDesc(scheduleId).stream()
                .map(CommentResponseDto::from)
                .collect(Collectors.toList());
    }

    /**
     * ✅ 댓글을 수정합니다. 작성자 본인만 수정 가능.
     *
     * @param commentId 댓글 ID
     * @param requestDto 댓글 수정 요청 데이터
     * @return 수정된 댓글 응답 DTO
     */
    public CommentResponseDto updateComment(Long commentId, CommentRequestDto requestDto) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new CustomException(ErrorCode.COMMENT_NOT_FOUND));

        if (!comment.getUser().getId().equals(requestDto.getUserId())) {
            throw new CustomException(ErrorCode.UNAUTHORIZED);
        }

        comment.update(requestDto.getContent());
        return CommentResponseDto.from(comment);
    }

    /**
     * ✅ 댓글을 삭제합니다. 작성자 본인만 삭제 가능.
     *
     * @param commentId 댓글 ID
     * @param userId 사용자 ID
     */
    public void deleteComment(Long commentId, Long userId) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new CustomException(ErrorCode.COMMENT_NOT_FOUND));

        if (!comment.getUser().getId().equals(userId)) {
            throw new CustomException(ErrorCode.UNAUTHORIZED);
        }

        commentRepository.delete(comment);
    }
}