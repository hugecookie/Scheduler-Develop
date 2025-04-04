package org.example.service;

import org.example.exception.CustomException;
import org.example.exception.ErrorCode;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.dto.ScheduleRequestDto;
import org.example.dto.ScheduleResponseDto;
import org.example.dto.ScheduleListResponseDto;
import org.example.entity.Schedule;
import org.example.entity.User;
import org.example.repository.ScheduleRepository;
import org.example.repository.UserRepository;
import org.example.repository.CommentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * ✅ 일정 관련 비즈니스 로직을 처리하는 서비스 클래스입니다.
 */
@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final UserRepository userRepository;
    private final CommentRepository commentRepository;

    /**
     * ✅ 일정을 등록합니다.
     *
     * @param requestDto 일정 등록 요청 데이터
     * @return 등록된 일정의 응답 DTO
     */
    public ScheduleResponseDto createSchedule(ScheduleRequestDto requestDto) {
        User user = userRepository.findById(requestDto.getUserId())
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));
        Schedule schedule = new Schedule(
                requestDto.getTitle(),
                requestDto.getContent(),
                user
        );
        scheduleRepository.save(schedule);
        return ScheduleResponseDto.from(schedule);
    }

    /**
     * ✅ 전체 일정을 생성일 기준 내림차순으로 조회합니다.
     *
     * @return 일정 응답 DTO 리스트
     */
    @Transactional(readOnly = true)
    public List<ScheduleResponseDto> getSchedules() {
        return scheduleRepository.findAllByOrderByCreatedAtDesc().stream()
                .map(ScheduleResponseDto::from)
                .collect(Collectors.toList());
    }

    /**
     * ✅ 특정 ID의 일정을 조회합니다.
     *
     * @param id 일정 ID
     * @return 일정 응답 DTO
     */
    @Transactional(readOnly = true)
    public ScheduleResponseDto getSchedule(Long id) {
        Schedule schedule = scheduleRepository.findById(id)
                .orElseThrow(() -> new CustomException(ErrorCode.SCHEDULE_NOT_FOUND));
        return ScheduleResponseDto.from(schedule);
    }

    /**
     * ✅ 특정 ID의 일정을 수정합니다.
     *
     * @param id 일정 ID
     * @param requestDto 일정 수정 요청 데이터
     * @return 수정된 일정 응답 DTO
     */
    public ScheduleResponseDto updateSchedule(Long id, ScheduleRequestDto requestDto) {
        Schedule schedule = scheduleRepository.findById(id)
                .orElseThrow(() -> new CustomException(ErrorCode.SCHEDULE_NOT_FOUND));
        schedule.update(requestDto);
        return ScheduleResponseDto.from(schedule);
    }

    /**
     * ✅ 특정 ID의 일정을 삭제합니다.
     *
     * @param id 일정 ID
     */
    public void deleteSchedule(Long id) {
        if (!scheduleRepository.existsById(id)) {
            throw new CustomException(ErrorCode.SCHEDULE_NOT_FOUND);
        }
        scheduleRepository.deleteById(id);
    }

    /**
     * ✅ 수정일 기준 내림차순으로 일정 페이지를 조회합니다.
     *
     * @param pageable 페이지 요청 정보
     * @return 일정 응답 DTO 페이지
     */
    public Page<ScheduleListResponseDto> getSchedules(Pageable pageable) {
        return scheduleRepository.findAllByOrderByUpdatedAtDesc(pageable)
                .map(schedule -> ScheduleListResponseDto.from(
                        schedule,
                        commentRepository.countByScheduleId(schedule.getId())
                ));
    }
}