package com.miracle.userservice.service;

import com.miracle.userservice.dto.request.UserJoinRequestDto;

public interface UserService {

    /**
     * 회원 가입 요청을 처리하는 메서드.
     *
     * @param dto 회원 가입 정보
     * @throws NullPointerException {@code dto}가 null일 경우
     * @author chocola
     */
    void join(UserJoinRequestDto dto);
}
