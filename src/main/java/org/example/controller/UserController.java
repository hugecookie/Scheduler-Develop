package org.example.controller;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.dto.LoginRequestDto;
import org.example.dto.LoginResponseDto;
import org.example.dto.UserRequestDto;
import org.example.dto.UserResponseDto;
import org.example.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * ✅ 유저 관련 API 요청을 처리하는 컨트롤러입니다.
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    /**
     * ✅ 유저를 생성합니다.
     *
     * @param requestDto 유저 요청 데이터
     * @return 생성된 유저 응답 DTO
     */
    @PostMapping
    public ResponseEntity<UserResponseDto> createUser(@RequestBody @Valid UserRequestDto requestDto) {
        return ResponseEntity.ok(userService.createUser(requestDto));
    }

    /**
     * ✅ 전체 유저 목록을 조회합니다.
     *
     * @return 유저 응답 DTO 리스트
     */
    @GetMapping
    public ResponseEntity<List<UserResponseDto>> getAllUsers() {
        return ResponseEntity.ok(userService.getUsers());
    }

    /**
     * ✅ 특정 유저를 조회합니다.
     *
     * @param id 유저 ID
     * @return 유저 응답 DTO
     */
    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> getUser(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getUser(id));
    }

    /**
     * ✅ 유저 정보를 수정합니다.
     *
     * @param id 유저 ID
     * @param requestDto 수정할 유저 데이터
     * @return 수정된 유저 응답 DTO
     */
    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDto> updateUser(@PathVariable Long id,
                                                      @RequestBody @Valid UserRequestDto requestDto) {
        return ResponseEntity.ok(userService.updateUser(id, requestDto));
    }

    /**
     * ✅ 유저를 삭제합니다.
     *
     * @param id 유저 ID
     * @return 빈 응답
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok().build();
    }

    /**
     * ✅ 유저 로그인 처리를 수행합니다.
     *
     * @param requestDto 로그인 요청 데이터
     * @param response 쿠키 저장을 위한 HttpServletResponse
     * @return 로그인 응답 DTO
     */
    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody @Valid LoginRequestDto requestDto,
                                                  HttpServletResponse response) {
        return ResponseEntity.ok(userService.login(requestDto, response));
    }
}
