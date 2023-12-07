package com.miracle.userservice.service;

import com.miracle.userservice.dto.request.InterviewPostRequestDto;
import com.miracle.userservice.dto.response.InterviewResponseDto;
import com.miracle.userservice.exception.NoSuchInterviewException;
import com.miracle.userservice.exception.NoSuchApplicationLetterException;
import com.miracle.userservice.exception.DuplicateInterviewException;
import com.miracle.userservice.exception.OverflowException;

public interface InterviewService {

    /**
     * 지원한 공고의 면접 정보를 반환하는 메서드
     *
     * @param interviewId 면접 ID
     * @return 면접 정보가 담긴 {@code InterviewResponseDto}를 반환 (면접 ID가 null이면 null 반환)
     * @throws NoSuchInterviewException If interview doesn't exist
     * @author hazzokko
     * */
    InterviewResponseDto getInterviews(Long interviewId);

    /**
     * 면접 정보를 등록하는 메서드
     * 면접 정보는 최대 5개까지 저장 가능
     *
     * @param userId 유저 ID
     * @param dto    면접 정보
     * @return true
     * @throws NullPointerException             If {@code userId} or {@code dto} is null
     * @throws NoSuchApplicationLetterException If applicationLetter doesn't exist
     * @throws DuplicateInterviewException      If interview already exist
     * @throws OverflowException                해당 유저의 면접 정보를 5개 이상 등록할 경우
     * @author hazzokko
     * */
    boolean postInterview(Long userId, InterviewPostRequestDto dto);

    /**
     * 면접 정보를 수정하는 메서드
     *
     * @param interviewId 면접 ID
     * @param dto         면접 정보
     * @return true
     * @throws NullPointerException             If {@code interviewId} or {@code dto} is null
     * @throws NoSuchApplicationLetterException If applicationLetter doesn't exist
     * @throws NoSuchInterviewException         If interview doesn't exist
     * @throws OverflowException                해당 유저의 면접 정보를 5개 이상 등록할 경우
     * @author hazzokko
     * */
    boolean updateInterview(Long interviewId, InterviewPostRequestDto dto);

    /**
     * 면접 정보를 삭제하는 메서드
     *
     * @param interviewId 면접 ID
     * @return true
     * @throws NullPointerException     If {@code interviewId} is null
     * @throws NoSuchInterviewException If interview doesn't exist
     * @author hazzokko
     * */
    boolean deleteInterview(Long interviewId);
}
