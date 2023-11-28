package com.miracle.userservice.service;

import com.miracle.userservice.dto.request.UserJoinRequestDto;
import com.miracle.userservice.dto.request.UserLoginRequestDto;
import com.miracle.userservice.dto.request.validation.util.ValidationDefaultMsgUtil;
import com.miracle.userservice.dto.response.UserBaseInfoResponseDto;
import com.miracle.userservice.dto.response.UserInfoResponseDto;
import com.miracle.userservice.entity.User;
import com.miracle.userservice.exception.DuplicateEmailException;
import com.miracle.userservice.exception.InvalidEmailException;
import com.miracle.userservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
            throw new DuplicateEmailException("400_1", "이메일 중복입니다.");
        }

        User user = dto.transformToUser();
        userRepository.save(user);
    }

    @Transactional(readOnly = true)
    @Override
    public boolean checkDuplicate(String email) {
        validEmail(email);
        return userRepository.existsByEmail(email);
    }

    private void validEmail(String email) {
        boolean match = email.matches("^[\\w.-]+@[a-zA-Z\\d.-]+\\.[a-zA-Z]{2,}$");

        if (!match) {
            throw new InvalidEmailException(ValidationDefaultMsgUtil.CheckDuplicate.EMAIL);
        }
    }

    @Transactional(readOnly = true)
    @Override
    public UserBaseInfoResponseDto getUserBaseInfo(Long userId) {
        return userRepository.findUserBaseInfoResponseDtoById(userId);
    }

    @Transactional(readOnly = true)
    @Override
    public UserInfoResponseDto getUserInfo(Long userId) {
        User user = userRepository.findById(userId).get();
        return UserInfoResponseDto.builder()
                .id(user.getId())
                .name(user.getName())
                .password(user.getPassword())
                .phone(user.getPhone())
                .birth(user.getBirth())
                .address(user.getAddress())
                .stackIdSet(user.getStackIdSet())
                .build();
    }
}
