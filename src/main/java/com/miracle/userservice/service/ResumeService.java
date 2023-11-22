package com.miracle.userservice.service;

import com.miracle.userservice.dto.response.ResumeResponseDto;
import com.miracle.userservice.exception.NoSuchResumeException;

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
}
