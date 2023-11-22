package com.miracle.userservice.service;

import com.miracle.userservice.dto.request.UserJoinRequestDto;
import com.miracle.userservice.dto.request.UserLoginRequestDto;
import com.miracle.userservice.entity.User;
import com.miracle.userservice.exception.DuplicateEmailException;
import com.miracle.userservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Objects;
import java.util.Optional;

@RequiredArgsConstructor
@Transactional
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public Optional<User> login(UserLoginRequestDto dto) {
        String errorMessage = "UserLoginRequestDto is null";
        Objects.requireNonNull(dto, errorMessage);

        String email = dto.getEmail();
        int password = dto.getPassword().hashCode();
        return userRepository.findByEmailAndPassword(email, password);
    }

    @Override
    public void join(UserJoinRequestDto dto) {
        String errorMessage = "UserJoinRequestDto is null";
        Objects.requireNonNull(dto, errorMessage);

        String email = dto.getEmail();
        if (userRepository.existsByEmail(email)) {
            throw new DuplicateEmailException("이메일 중복입니다.");
        }

        User user = dto.transformToUser();
        userRepository.save(user);
    }
}
