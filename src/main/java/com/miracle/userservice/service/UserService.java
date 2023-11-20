package com.miracle.userservice.service;

import com.miracle.userservice.dto.request.UserLoginRequestDto;

public interface UserService {

    /**
     * 유저의 로그인 정보(이메일, 비밀번호)를 받아서 DB와 일치하는지 확인하는 메서드
     *
     * @param dto 로그인 정보(이메일, 비밀번호)
     * @throws NullPointerException {@code dto}가 null일 경우
     * @author hazzokko
     * @return boolean 타입으로 반환(DB에 로그인 정보 존재 시 true)
     * */
    boolean login(UserLoginRequestDto dto);
}
