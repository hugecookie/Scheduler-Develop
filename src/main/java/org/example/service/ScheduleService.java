package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.dto.ScheduleRequestDto;
import org.example.dto.ScheduleResponseDto;
import org.example.entity.Schedule;
import org.example.entity.User;
import org.example.repository.ScheduleRepository;
import org.example.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

// ✅ Schedule 관련 비즈니스 로직을 담당하는 서비스 클래스
@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final UserRepository userRepository;

    // ✅ 일정 등록
    public ScheduleResponseDto createSchedule(ScheduleRequestDto dto) {
        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("해당 유저가 없습니다."));
        Schedule schedule = new Schedule(dto, user);
        scheduleRepository.save(schedule);
        return new ScheduleResponseDto(schedule);
    }

    // ✅ 일정 전체 조회
    public List<ScheduleResponseDto> getSchedules() {
        return scheduleRepository.findAllByOrderByCreatedAtDesc().stream()
                .map(ScheduleResponseDto::new)
                .collect(Collectors.toList());
    }

    // ✅ 일정 단건 조회
    public ScheduleResponseDto getSchedule(Long id) {
        Schedule schedule = scheduleRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 일정이 존재하지 않습니다."));
        return new ScheduleResponseDto(schedule);
    }

    // ✅ 일정 수정
    public ScheduleResponseDto updateSchedule(Long id, ScheduleRequestDto requestDto) {
        Schedule schedule = scheduleRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 일정이 존재하지 않습니다."));
        schedule.update(requestDto);
        return new ScheduleResponseDto(schedule);
    }

    // ✅ 일정 삭제
    public void deleteSchedule(Long id) {
        scheduleRepository.deleteById(id);
    }
}
