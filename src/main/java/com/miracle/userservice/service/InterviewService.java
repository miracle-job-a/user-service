package com.miracle.userservice.service;

import com.miracle.userservice.dto.response.InterviewResponseDto;
import com.miracle.userservice.exception.NoSuchApplicationLetterException;
import com.miracle.userservice.exception.NoSuchInterviewException;

public interface InterviewService {

    /**
     * 지원한 공고의 면접 정보를 반환하는 메서드
     *
     * @param applicationLetterId 지원서 ID
     * @return 면접 정보가 담긴 {@code InterviewResponseDto}를 반환 (면접 정보가 없을 시 null 반환)
     * @throws NullPointerException             If {@code applicationLetterId} is null
     * @throws NoSuchApplicationLetterException If applicationLetter doesn't exist
     * @author hazzokko
     * */
    InterviewResponseDto getInterviews(Long applicationLetterId);

    /**
     * 면접 정보를 삭제하는 메서드
     *
     * @param interviewId 면접 ID
     * @return true
     * @throws NullPointerException     If {@code applicationLetterId} is null
     * @throws NoSuchInterviewException If interview doesn't exist
     * @author hazzokko
     * */
    boolean deleteInterview(Long interviewId);
}
