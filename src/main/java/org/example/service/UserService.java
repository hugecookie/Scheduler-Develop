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

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    // ✅ 유저 생성
    public UserResponseDto createUser(UserRequestDto dto) {
        // 이메일 중복 시 메시지 출력
        if (userRepository.findByEmail(dto.getEmail()).isPresent()) {
            throw new CustomException(ErrorCode.DUPLICATED_EMAIL);
        }

        // 비밀번호 암호화
        String encodedPassword = passwordEncoder.encode(dto.getPassword());

        // 암호화된 비밀번호로 유저 객체 생성
        User user = new User(dto.getUsername(), dto.getEmail(), encodedPassword);
        userRepository.save(user);
        return new UserResponseDto(user);
    }

    // ✅ 유저 전체 조회
    @Transactional(readOnly = true)
    public List<UserResponseDto> getUsers() {
        return userRepository.findAll().stream()
                .map(UserResponseDto::new)
                .collect(Collectors.toList());
    }

    // ✅ 유저 단건 조회
    @Transactional(readOnly = true)
    public UserResponseDto getUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));
        return new UserResponseDto(user);
    }

    // ✅ 유저 정보 수정
    public UserResponseDto updateUser(Long id, UserRequestDto dto) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));
        user.update(dto.getEmail(), dto.getPassword());
        return new UserResponseDto(user);
    }

    // ✅ 유저 삭제
    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new CustomException(ErrorCode.USER_NOT_FOUND);
        }
        userRepository.deleteById(id);
    }

    // ✅ 유저 로그인 후 쿠키 생성
    public LoginResponseDto login(LoginRequestDto requestDto, HttpServletResponse response) {
        // 이메일로 유저 조회
        User user = userRepository.findByEmail(requestDto.getEmail())
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));

        // 비밀번호 비교 (암호화된 비밀번호와 입력된 평문 비교)
        if (!passwordEncoder.matches(requestDto.getPassword(), user.getPassword())) {
            throw new CustomException(ErrorCode.PASSWORD_MISMATCH);
        }

        // 로그인 성공 시 쿠키 생성
        Cookie cookie = new Cookie("userId", String.valueOf(user.getId()));
        cookie.setPath("/");
        cookie.setMaxAge(60 * 60); // 1시간
        response.addCookie(cookie);

        return new LoginResponseDto(user.getId(), user.getUsername());
    }
}
