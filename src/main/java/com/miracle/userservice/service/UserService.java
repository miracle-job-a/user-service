package com.miracle.userservice.service;

import com.miracle.userservice.dto.request.UserLoginRequestDto;
import com.miracle.userservice.dto.request.UserJoinRequestDto;
import com.miracle.userservice.exception.DuplicateEmailException;

public interface UserService {

    /**
     * 유저의 로그인 정보(이메일, 비밀번호)를 받아서 DB와 일치하는지 확인하는 메서드
     *
     * @param dto 로그인 정보(이메일, 비밀번호)
     * @throws NullPointerException {@code dto}가 null일 경우
     * @author hazzokko
     * @return DB에 로그인 정보 존재 시 true
     * */
    boolean login(UserLoginRequestDto dto);

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

    /**
     * 이메일 중복 확인을 처리하는 메서드
     * 해당 이메일이 DB에 저장되어 있는지 확인한다. 저장되어 있다면 true, 그렇지 않으면 false를 반환한다.
     *
     * @param email 유저 이메일
     * @return {@code email}이 기 저장되어 있다면 true, 그렇지 않으면 false를 반환
     * @author chocola
     */
    boolean checkDuplicate(String email);
}
