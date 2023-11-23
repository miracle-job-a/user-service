package com.miracle.userservice.service;

import com.miracle.userservice.dto.response.CoverLetterListResponseDto;

import java.util.List;

public interface CoverLetterService {
    /**
     * @param userId
     * @throws NullPointerException {@code userId}가 없을 경우 예외 발생
     * @return 자기소개서 정보가 담긴 {@code CoverLetterListResponseDto}를 리스트에 담아서 반환
     * @author hazzokko
     * */
    List<CoverLetterListResponseDto> getCoverLetterList(Long userId);
}
