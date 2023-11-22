package com.miracle.userservice.service;

import com.miracle.userservice.dto.request.ResumePostRequestDto;
import com.miracle.userservice.dto.response.ResumeResponseDto;
import com.miracle.userservice.exception.NoSuchResumeException;

import java.util.NoSuchElementException;

public interface ResumeService {

    /**
     * 회원의 이력서를 조회
     *
     * @param id 이력서 id
     * @return 이력서 상세 데이터
     * @throws NullPointerException  If {@code id} is null
     * @throws NoSuchResumeException If resume doesn't exist
     * @author chocola
     */
    ResumeResponseDto getResumeDetail(Long id);

    /**
     * 회원의 이력서를 저장
     *
     * @param dto 이력서 정보
     * @return true
     * @throws NullPointerException If {@code dto} is null
     * @throws NoSuchElementException If user doesn't exist
     * @author chocola
     */
    boolean postResume(ResumePostRequestDto dto);
}
