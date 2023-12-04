package com.miracle.userservice.service;

import com.miracle.userservice.dto.request.UserJoinRequestDto;
import com.miracle.userservice.dto.request.UserLoginRequestDto;
import com.miracle.userservice.dto.request.UserUpdateInfoRequestDto;
import com.miracle.userservice.dto.response.UserBaseInfoResponseDto;
import com.miracle.userservice.dto.response.UserInfoResponseDto;
import com.miracle.userservice.dto.response.UserJoinListResponseDto;
import com.miracle.userservice.dto.response.UserListResponseDto;
import com.miracle.userservice.entity.User;
import com.miracle.userservice.exception.DuplicateEmailException;
import com.miracle.userservice.exception.InvalidEmailException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface UserService {

    /**
     * 유저의 로그인 정보(이메일, 비밀번호)를 받아서 DB와 일치하는지 확인하는 메서드
     *
     * @param dto 로그인 정보(이메일, 비밀번호)
     * @return 로그인에 성공했을 경우 해당 User 객체를 {@code Optional}로 감싸서 반환
     * @throws NullPointerException {@code dto}가 null일 경우
     * @author hazzokko, chocola
     */
    Optional<User> login(UserLoginRequestDto dto);

    /**
     * 회원 가입 요청을 처리하는 메서드
     * 이메일 중복 체크가 선행되며, 기 등록된 이메일이 아닐 경우 회원 가입이 진행된다.
     * 이미 등록된 이메일인 경우 {@code DuplicateEmailException}이 발생한다.
     *
     * @param dto 회원 가입 정보
     * @throws NullPointerException    {@code dto}가 null일 경우
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
     * @throws InvalidEmailException {@code email}이 올바른 형식이 아닐 경우
     * @author chocola
     */
    boolean checkDuplicate(String email);

    /**
     * 특정 유저의 기본 정보를 반환하는 메서드
     * 기본 정보에는 이메일, 이름, 전화번호, 생년월일, 주소, 스택 ID 목록을 포함한다.
     *
     * @param userId 유저의 ID
     * @return 유저의 기본 정보가 담긴 DTO(email, name, phone, birth, address, stackIdSet)
     * @author chocola
     */
    UserBaseInfoResponseDto getUserBaseInfo(Long userId);

    /**
     * 특정 유저의 정보를 반환하는 메서드
     * 유저 정보에는 아이디, 이름, 생년월일, 비밀번호, 전화번호, 주소, 스택 아이디 목록을 포함한다.
     *
     * @param userId 유저의 ID
     * @return 유저의 정보가 담긴 DTO(id, name, birth, password, phone, address, stackIdSet)
     * @author chocola
     */
    UserInfoResponseDto getUserInfo(Long userId);

    /**
     * 특정 유저의 정보를 수정하는 메서드
     *
     * @param userId 유저의 ID
     * @param dto    유저 정보 수정 데이터
     * @return true
     * @author chocola
     */
    boolean updateUserInfo(Long userId, UserUpdateInfoRequestDto dto);

    /**
     * 특정 유저 회원 탈퇴 메서드
     *
     * @param userId 유저의 ID
     * @return true
     * @author chocola
     */
    boolean deleteUser(Long userId);

    /**
     * 유저 목록을 조회하는 메서드
     *
     * @param pageable 유저 목록 페이징 정보
     * @return 유저 목록이 담긴 {@code Page}. 유저 목록 정보는 ID, 이메일, 이름, 주소, 회원 가입 날짜를 포함한다.
     * @author chocola
     */
    Page<UserListResponseDto> getUserList(Pageable pageable);

    /**
     * 특정 날짜의 유저 회원 가입 목록을 조회
     *
     * @param date 조회하고자 하는 날짜
     * @return {@code date}에 회원 가입한 유저 리스트
     * @author chocola
     */
    List<UserJoinListResponseDto> getJoinList(LocalDate date);
}
