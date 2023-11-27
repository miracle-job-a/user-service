package com.miracle.userservice.service;

import com.miracle.userservice.dto.response.ApplicationLetterResponseDto;
import com.miracle.userservice.exception.NoSuchCoverLetterException;
import com.miracle.userservice.exception.NoSuchResumeException;

public interface ApplicationLetterService {

    /**
     * 특정 공고의 지원자수를 반환하는 메서드
     *
     * @param postId 공고 ID
     * @return 해당 공고의 지원자수
     * @author chocola
     */
    long getNumberOfApplicant(Long postId);

    /**
     * 공고 상세 페이지의 지원하기 팝업에서 유저의 이력서 및 자소서 제목 목록 조회
     *
     * @param userId 유저 ID
     * @return
     * @throws NullPointerException If {@code userId} is null
     * @throws NoSuchResumeException If resume doesn't exist
     * @throws NoSuchCoverLetterException If coverLetter doesn't exist
     * @author hazzokko
     * */
    ApplicationLetterResponseDto getResumeAndCoverLetterList(Long userId);
}
