package com.miracle.userservice.service;

import com.miracle.userservice.dto.request.UserJoinRequestDto;
import com.miracle.userservice.dto.request.UserLoginRequestDto;
import com.miracle.userservice.entity.User;
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
    public boolean login(UserLoginRequestDto dto) {
        String errorMessage = "UserLoginRequestDto is null";
        Objects.requireNonNull(dto, errorMessage);

        Optional<User> user = userRepository.findByEmailAndPassword(dto.getEmail(), dto.getPassword().hashCode());

        return user.isPresent();
    }

    @Override
    public void join(UserJoinRequestDto dto) {
        String errorMessage = "UserJoinRequestDto is null";
        Objects.requireNonNull(dto, errorMessage);

        User user = dto.transformToUser();
        userRepository.save(user);
    }

    @Override
    public boolean checkDuplicate(String email) {
        return userRepository.existsByEmail(email);
    }
}
