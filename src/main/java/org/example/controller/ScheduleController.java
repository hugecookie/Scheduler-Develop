package org.example.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.dto.ScheduleListResponseDto;
import org.example.dto.ScheduleRequestDto;
import org.example.dto.ScheduleResponseDto;
import org.example.service.ScheduleService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * ✅ 일정 관련 API 요청을 처리하는 컨트롤러입니다.
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/schedules")
public class ScheduleController {

    private final ScheduleService scheduleService;

    /**
     * ✅ 일정 등록 요청을 처리합니다.
     *
     * @param requestDto 일정 생성 요청 데이터
     * @return 생성된 일정 응답 DTO
     */
    @PostMapping
    public ResponseEntity<ScheduleResponseDto> createSchedule(@RequestBody @Valid ScheduleRequestDto requestDto) {
        return ResponseEntity.ok(scheduleService.createSchedule(requestDto));
    }

    /**
     * ✅ 전체 일정 목록을 조회합니다.
     *
     * @return 일정 응답 DTO 리스트
     */
    @GetMapping
    public ResponseEntity<List<ScheduleResponseDto>> getAllSchedules() {
        return ResponseEntity.ok(scheduleService.getSchedules());
    }

    /**
     * ✅ 페이징된 일정 목록을 조회합니다.
     *
     * @param page 페이지 번호
     * @param size 페이지 크기
     * @return 페이징된 일정 응답 DTO 페이지
     */
    @GetMapping("/pages")
    public Page<ScheduleListResponseDto> getSchedules(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Pageable pageable = PageRequest.of(page, size, Sort.by("updatedAt").descending());
        return scheduleService.getSchedules(pageable);
    }

    /**
     * ✅ 단일 일정을 조회합니다.
     *
     * @param id 일정 ID
     * @return 일정 응답 DTO
     */
    @GetMapping("/{id}")
    public ResponseEntity<ScheduleResponseDto> getOneSchedule(@PathVariable Long id) {
        return ResponseEntity.ok(scheduleService.getSchedule(id));
    }

    /**
     * ✅ 일정을 수정합니다.
     *
     * @param id 일정 ID
     * @param requestDto 수정할 일정 데이터
     * @return 수정된 일정 응답 DTO
     */
    @PutMapping("/{id}")
    public ResponseEntity<ScheduleResponseDto> updateSchedule(@PathVariable Long id,
                                                              @RequestBody @Valid ScheduleRequestDto requestDto) {
        return ResponseEntity.ok(scheduleService.updateSchedule(id, requestDto));
    }

    /**
     * ✅ 일정을 삭제합니다.
     *
     * @param id 일정 ID
     * @return 빈 응답
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSchedule(@PathVariable Long id) {
        scheduleService.deleteSchedule(id);
        return ResponseEntity.ok().build();
    }
}