package com.miracle.userservice.service;

import com.miracle.userservice.dto.response.SearchCoverLetterResponseDto;

import java.util.List;
import java.util.NoSuchElementException;

public interface CoverLetterService {
    /**
     * @param userId
     * @throws NoSuchElementException {@code userId}가 없을 경우
     * @return 자기소개서 정보가 담긴 {@code CoverLetterResponseDto}를 리스트에 담아서 반환
     * @author hazzokko
     * */
    List<SearchCoverLetterResponseDto> getCoverLetterList(Long userId);
}
