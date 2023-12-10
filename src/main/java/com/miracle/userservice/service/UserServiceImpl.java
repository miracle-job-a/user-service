package com.miracle.userservice.service;

import com.miracle.userservice.dto.request.UserJoinRequestDto;
import com.miracle.userservice.dto.request.UserLoginRequestDto;
import com.miracle.userservice.dto.request.UserUpdateInfoRequestDto;
import com.miracle.userservice.dto.request.validation.util.ValidationDefaultMsgUtil;
import com.miracle.userservice.dto.response.UserBaseInfoResponseDto;
import com.miracle.userservice.dto.response.UserInfoResponseDto;
import com.miracle.userservice.dto.response.UserJoinListResponseDto;
import com.miracle.userservice.dto.response.UserListResponseDto;
import com.miracle.userservice.entity.User;
import com.miracle.userservice.exception.DuplicateEmailException;
import com.miracle.userservice.exception.InvalidEmailException;
import com.miracle.userservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public Optional<User> login(UserLoginRequestDto dto) {
        String errorMessage = "UserLoginRequestDto is null";
        Objects.requireNonNull(dto, errorMessage);

        String email = dto.getEmail();
        String password = dto.getPassword();
        return userRepository.findByEmailAndPassword(email, password);
    }

    @Transactional
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

    @Override
    public UserBaseInfoResponseDto getUserBaseInfo(Long userId) {
        User user = userRepository.findById(userId).orElseThrow();
        return UserBaseInfoResponseDto.builder()
                .email(user.getEmail())
                .name(user.getName())
                .address(user.getAddress())
                .phone(user.getPhone())
                .birth(user.getBirth())
                .stackIdSet(user.getStackIdSet())
                .build();
    }

    @Override
    public UserInfoResponseDto getUserInfo(Long userId) {
        User user = userRepository.findById(userId).orElseThrow();
        return UserInfoResponseDto.builder()
                .id(user.getId())
                .name(user.getName())
                .phone(user.getPhone())
                .birth(user.getBirth())
                .address(user.getAddress())
                .stackIdSet(user.getStackIdSet())
                .build();
    }

    @Transactional
    @Override
    public boolean updateUserInfo(Long userId, UserUpdateInfoRequestDto dto) {
        Optional<User> userOpt = userRepository.findById(userId);
        if (userOpt.isEmpty()) return false;

        User user = userOpt.get();
        user.update(dto);
        return true;
    }

    @Transactional
    @Override
    public boolean deleteUser(Long userId) {
        userRepository.deleteById(userId);
        return true;
    }

    @Override
    public Page<UserListResponseDto> getUserList(Pageable pageable) {
        return userRepository.findAllUserList(pageable);
    }

    @Override
    public Page<UserJoinListResponseDto> getJoinList(LocalDate date, Pageable pageable) {
        LocalDateTime startDate = date.atStartOfDay();
        LocalDateTime endDate = date.plusDays(1L).atStartOfDay();
        return userRepository.findJoinList(startDate, endDate, pageable);
    }

    @Override
    public Map<String, Object> getUserJoinNumber(LocalDate date) {
        LocalDateTime startDate = date.atStartOfDay();
        LocalDateTime endDate = date.plusDays(1L).atStartOfDay();
        return userRepository.countByCreatedAt(startDate, endDate);
    }
}
