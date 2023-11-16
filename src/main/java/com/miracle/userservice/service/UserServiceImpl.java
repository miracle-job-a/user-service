package com.miracle.userservice.service;

import com.miracle.userservice.dto.request.UserLoginRequestDto;
import com.miracle.userservice.entity.User;
import com.miracle.userservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Objects;

@RequiredArgsConstructor
@Transactional
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public void login(UserLoginRequestDto dto) {
        Objects.requireNonNull(dto);
        User user = User.check(dto);
        userRepository.findByEmailAndPassword(user.getEmail(), user.getPassword());
    }
}
