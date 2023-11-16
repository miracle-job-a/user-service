package com.miracle.userservice.service;

import com.miracle.userservice.dto.request.UserLoginRequestDto;
import com.miracle.userservice.entity.User;
import com.miracle.userservice.exception.NoSuchEmailException;
import com.miracle.userservice.exception.PasswordMismatchException;

import java.util.Optional;

public interface UserService {

    /**
     * 유저의 로그인 정보(이메일, 비밀번호)를 받아서 DB와 일치하는지 확인하는 메서드
     *
     * @param dto 로그인 정보(이메일, 비밀번호)
     * @throws NoSuchEmailException {@code String email} 값을 DB에서 찾을 수 없을 때 발생
     * @throws PasswordMismatchException {@code int password} 값이 일치하지 않을 때 발생
     * @throws NullPointerException {@code dto}가 null일 경우
     * @author hazzokko
     * @return*/
    void login(UserLoginRequestDto dto);
}
