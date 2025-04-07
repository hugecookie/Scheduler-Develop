package org.example.service;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.example.config.PasswordEncoder;
import org.example.dto.LoginRequestDto;
import org.example.dto.LoginResponseDto;
import org.example.dto.UserRequestDto;
import org.example.dto.UserResponseDto;
import org.example.entity.User;
import org.example.exception.CustomException;
import org.example.exception.ErrorCode;
import org.example.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.example.jwt.JwtUtil;

import java.util.List;
import java.util.stream.Collectors;

/**
 * ✅ 유저 관련 비즈니스 로직을 처리하는 서비스 클래스입니다.
 */
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    /**
     * ✅ 유저를 생성합니다. (이메일 중복 체크 및 비밀번호 암호화 포함)
     *
     * @param dto 유저 생성 요청 데이터
     * @return 생성된 유저 응답 DTO
     */
    public UserResponseDto createUser(UserRequestDto dto) {
        if (userRepository.findByEmail(dto.getEmail()).isPresent()) {
            throw new CustomException(ErrorCode.DUPLICATED_EMAIL);
        }

        String encodedPassword = passwordEncoder.encode(dto.getPassword());
        User user = new User(dto.getUsername(), dto.getEmail(), encodedPassword);
        userRepository.save(user);
        return UserResponseDto.from(user);
    }

    /**
     * ✅ 전체 유저를 조회합니다.
     *
     * @return 유저 응답 DTO 리스트
     */
    @Transactional(readOnly = true)
    public List<UserResponseDto> getUsers() {
        return userRepository.findAll().stream()
                .map(UserResponseDto::from)
                .collect(Collectors.toList());
    }

    /**
     * ✅ 특정 ID의 유저를 조회합니다.
     *
     * @param id 유저 ID
     * @return 유저 응답 DTO
     */
    @Transactional(readOnly = true)
    public UserResponseDto getUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));
        return UserResponseDto.from(user);
    }

    /**
     * ✅ 유저 정보를 수정합니다.
     *
     * @param id 유저 ID
     * @param dto 유저 수정 요청 데이터
     * @return 수정된 유저 응답 DTO
     */
    public UserResponseDto updateUser(Long id, UserRequestDto dto) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));
        user.update(dto.getEmail(), dto.getPassword());
        return UserResponseDto.from(user);
    }

    /**
     * ✅ 유저를 삭제합니다.
     *
     * @param id 유저 ID
     */
    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new CustomException(ErrorCode.USER_NOT_FOUND);
        }
        userRepository.deleteById(id);
    }

    /**
     * ✅ 유저 로그인 시 이메일/비밀번호 검증 및 쿠키 발급
     *
     * @param requestDto 로그인 요청 데이터
     * @param response HttpServletResponse에 쿠키 추가
     * @return 로그인 응답 DTO
     */
    public LoginResponseDto login(LoginRequestDto requestDto, HttpServletResponse response) {
        User user = userRepository.findByEmail(requestDto.getEmail())
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));

        if (!passwordEncoder.matches(requestDto.getPassword(), user.getPassword())) {
            throw new CustomException(ErrorCode.PASSWORD_MISMATCH);
        }

        // ✅ JWT 토큰 발급
        String token = jwtUtil.createToken(user.getId(), user.getUsername());

        // ✅ 헤더에 Authorization: Bearer {token} 추가
        response.setHeader("Authorization", "Bearer " + token);

        return LoginResponseDto.from(user, token);
    }
}
