package com.miracle.userservice.service;

import com.miracle.userservice.controller.Requester;
import com.miracle.userservice.dto.request.ResumePostRequestDto;
import com.miracle.userservice.dto.response.ResumeResponseDto;
import com.miracle.userservice.exception.NoSuchResumeException;

import java.util.NoSuchElementException;

public interface ResumeService {

    /**
     * 회원의 이력서를 조회
     * 유저가 조회했을 경우, 비공개 이력서도 조회 되어야함
     * 기업이 조회했을 경우, 공개 이력서만 조회 되어야함
     *
     * @param id        이력서 id
     * @param requester 이력서 조회를 요청한 멤버의 타입
     * @return 이력서 상세 데이터
     * @throws NullPointerException  If {@code id} is null
     * @throws NoSuchResumeException If resume doesn't exist
     * @author chocola
     */
    ResumeResponseDto getResumeDetail(Long id, Requester requester);

    /**
     * 회원의 이력서를 저장
     *
     * @param dto 이력서 정보
     * @return true
     * @throws NullPointerException   If {@code dto} is null
     * @throws NoSuchElementException If resume doesn't exist
     * @author chocola
     */
    boolean postResume(ResumePostRequestDto dto);

    /**
     * 회원의 이력서를 수정
     *
     * @param id  이력서 아이디
     * @param dto 이력서 정보
     * @return true
     * @throws NullPointerException   If {@code id}, {@code dto} is null
     * @throws NoSuchElementException If resume doesn't exist
     * @author chocola
     */
    boolean updateResume(Long id, ResumePostRequestDto dto);

    /**
     * 회원의 이력서를 삭제
     *
     * @param id 이력서 id
     * @return true
     * @throws NullPointerException  If {@code id} is null
     * @throws NoSuchResumeException If resume doesn't exist
     * @author chocola
     */
    boolean deleteResume(Long id);
}
