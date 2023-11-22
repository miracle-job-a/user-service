package com.miracle.userservice.service;

import com.miracle.userservice.dto.request.UserJoinRequestDto;
import com.miracle.userservice.dto.request.UserLoginRequestDto;
import com.miracle.userservice.entity.User;
import com.miracle.userservice.exception.DuplicateEmailException;

import java.util.Optional;

public interface UserService {

    /**
     * 유저의 로그인 정보(이메일, 비밀번호)를 받아서 DB와 일치하는지 확인하는 메서드
     *
     * @param dto 로그인 정보(이메일, 비밀번호)
     * @throws NullPointerException {@code dto}가 null일 경우
     * @author hazzokko, chocola
     * @return 로그인에 성공했을 경우 해당 User 객체를 {@code Optional}로 감싸서 반환
     * */
    Optional<User> login(UserLoginRequestDto dto);

    /**
     * 회원 가입 요청을 처리하는 메서드
     * 이메일 중복 체크가 선행되며, 기 등록된 이메일이 아닐 경우 회원 가입이 진행된다.
     * 이미 등록된 이메일인 경우 {@code DuplicateEmailException}이 발생한다.
     *
     * @param dto 회원 가입 정보
     * @throws NullPointerException {@code dto}가 null일 경우
     * @throws DuplicateEmailException 기 등록된 이메일인 경우
     * @author chocola
     */
    void join(UserJoinRequestDto dto);
}
