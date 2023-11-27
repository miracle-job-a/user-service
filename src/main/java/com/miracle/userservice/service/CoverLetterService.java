package com.miracle.userservice.service;

import com.miracle.userservice.dto.request.CoverLetterPostRequestDto;
import com.miracle.userservice.dto.response.CoverLetterListResponseDto;
import com.miracle.userservice.dto.response.CoverLetterResponseDto;
import com.miracle.userservice.exception.NoSuchCoverLetterException;

import java.util.List;

public interface CoverLetterService {
    /**
     * 유저의 자기소개서 목록 조회
     *
     * @param userId 유저 ID
     * @return 자기소개서 정보가 담긴 {@code CoverLetterListResponseDto}를 리스트에 담아서 반환
     * @throws NullPointerException If {@code userId} is null
     * @author hazzokko
     * */
    List<CoverLetterListResponseDto> getCoverLetterList(Long userId);

    /**
     * 유저의 자기소개서 내용 조회
     *
     * @param id 자기소개서 ID
     * @return 자기소개서 상세 데이터 {@code CoverLetterResponseDto}를 반환
     * @throws NullPointerException If {@code id} is null
     * @throws NoSuchCoverLetterException If coverLetter doesn't exist
     * @author hazzokko
     * */
    CoverLetterResponseDto getCoverLetterDetail(Long id);

    /**
     * 유저의 자기소개서 등록
     *
     * @param dto 자기소개서 정보
     * @return true
     * @throws NullPointerException If {@code dto} is null
     * @author hazzokko
     * */
    boolean postCoverLetter(CoverLetterPostRequestDto dto);

    /**
     * 유저의 자기소개서 수정
     *
     * @param id 자기소개서 ID
     * @param dto 자기소개서 정보
     * @return true
     * @throws NullPointerException If {@code dto} is null
     * @throws NoSuchCoverLetterException If coverLetter doesn't exist
     * @author hazzokko
     * */
    boolean updateCoverLetter(Long id, CoverLetterPostRequestDto dto);

    /**
     * 유저의 자기소개서 삭제
     *
     * @param id 자기소개서 ID
     * @return true
     * @throws NullPointerException If {@code id} is null
     * @throws NoSuchCoverLetterException If coverLetter doesn't exist
     * @author hazzokko
     * */
    boolean deleteCoverLetter(Long id);
}
