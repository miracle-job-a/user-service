package com.miracle.userservice.service;

import com.miracle.userservice.dto.response.ApplicationLetterResponseDto;
import com.miracle.userservice.dto.response.CoverLetterInApplicationLetterResponseDto;
import com.miracle.userservice.dto.response.ResumeInApplicationLetterResponseDto;
import com.miracle.userservice.exception.NoSuchApplicationLetterException;

public interface ApplicationLetterService {

    /**
     * 특정 공고의 지원자 수를 반환하는 메서드
     *
     * @param postId 공고 ID
     * @return 해당 공고의 지원자 수
     * @author chocola
     */
    long getNumberOfApplicant(Long postId);

    /**
     * 공고 상세 페이지의 지원하기 팝업에서 유저의 이력서 및 자소서 제목 목록 조회
     *
     * @param userId 유저 ID
     * @return 이력서 및 자기소개서 목록 데이터 {@code ApplicationLetterResponseDto}를 반환
     * @throws NullPointerException If {@code userId} is null
     * @author hazzokko
     * */
    ApplicationLetterResponseDto getResumeAndCoverLetterList(Long userId);

    /**
     * 지원서 중 이력서 정보를 반환하는 메서드
     *
     * @param applicationLetterId 지원서 ID
     * @param userId 유저 ID
     * @return 이력서 데이터 {@code ResumeInApplicationLetterResponseDto} 를 반환
     * @throws NullPointerException If {@code applicationLetterId} is null
     * @throws NoSuchApplicationLetterException If applicationLetter doesn't exist
     * @author hazzokko
     * */
    ResumeInApplicationLetterResponseDto getResume(Long applicationLetterId, Long userId);

    /**
     * 지원서 중 자기소개서 정보를 반환하는 메서드
     *
     * @param applicationLetterId 지원서 ID
     * @return 자기소개서 데이터 {@code CoverLetterInApplicationLetterResponseDto} 를 반환
     * @throws NullPointerException If {@code applicationLetterId} is null
     * @throws NoSuchApplicationLetterException If applicationLetter doesn't exist
     * @author hazzokko
     * */
    CoverLetterInApplicationLetterResponseDto getCoverLetter(Long applicationLetterId);
}