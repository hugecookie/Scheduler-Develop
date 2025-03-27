package org.example.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.dto.ScheduleRequestDto;
import org.example.dto.ScheduleResponseDto;
import org.example.service.ScheduleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// ✅ 일정 관련 REST API를 담당하는 컨트롤러
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/schedules")
public class ScheduleController {

    private final ScheduleService scheduleService;

    // ✅ 일정 등록
    @PostMapping
    public ResponseEntity<ScheduleResponseDto> create(@RequestBody @Valid ScheduleRequestDto requestDto) {
        return ResponseEntity.ok(scheduleService.createSchedule(requestDto));
    }

    // ✅ 일정 전체 조회
    @GetMapping
    public ResponseEntity<List<ScheduleResponseDto>> getAll() {
        return ResponseEntity.ok(scheduleService.getSchedules());
    }

    // ✅ 일정 단건 조회
    @GetMapping("/{id}")
    public ResponseEntity<ScheduleResponseDto> getOne(@PathVariable Long id) {
        return ResponseEntity.ok(scheduleService.getSchedule(id));
    }

    // ✅ 일정 수정
    @PutMapping("/{id}")
    public ResponseEntity<ScheduleResponseDto> update(@PathVariable Long id,
                                                      @RequestBody @Valid ScheduleRequestDto requestDto) {
        return ResponseEntity.ok(scheduleService.updateSchedule(id, requestDto));
    }

    // ✅ 일정 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        scheduleService.deleteSchedule(id);
        return ResponseEntity.ok().build();
    }
}
