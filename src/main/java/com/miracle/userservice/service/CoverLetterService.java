package com.miracle.userservice.service;

import com.miracle.userservice.dto.response.CoverLetterListResponseDto;
import com.miracle.userservice.dto.response.CoverLetterResponseDto;
import com.miracle.userservice.exception.NoSuchCoverLetterException;

import java.util.List;

public interface CoverLetterService {
    /**
     * 유저의 자기소개서 목록을 조회
     *
     * @param userId 유저 ID
     * @return 자기소개서 정보가 담긴 {@code CoverLetterListResponseDto}를 리스트에 담아서 반환
     * @throws NullPointerException If {@code userId} is null
     * @author hazzokko
     * */
    List<CoverLetterListResponseDto> getCoverLetterList(Long userId);

    /**
     * 유저의 자기소개서 내용을 조회
     *
     * @param id 자기소개서 ID
     * @return 자기소개서 상세 데이터 {@code CoverLetterResponseDto}를 반환
     * @throws NullPointerException If {@code id} is null
     * @throws NoSuchCoverLetterException If CoverLetter doesn't exist
     * @author hazzokko
     * */
    CoverLetterResponseDto getCoverLetterDetail(Long id);
}
