package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.dto.UserRequestDto;
import org.example.dto.UserResponseDto;
import org.example.entity.User;
import org.example.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    // ✅ 유저 생성
    public UserResponseDto createUser(UserRequestDto dto) {
        User user = new User(dto.getUsername(), dto.getEmail(), dto.getPassword());
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
                .orElseThrow(() -> new IllegalArgumentException("해당 유저가 없습니다."));
        return new UserResponseDto(user);
    }

    // ✅ 유저 정보 수정
    public UserResponseDto updateUser(Long id, UserRequestDto dto) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 유저가 없습니다."));
        user.update(dto.getEmail(), dto.getPassword());
        return new UserResponseDto(user);
    }

    // ✅ 유저 삭제
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
