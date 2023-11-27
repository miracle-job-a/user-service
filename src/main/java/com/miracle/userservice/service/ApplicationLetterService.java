package com.miracle.userservice.service;

public interface ApplicationLetterService {

    /**
     * 특정 공고의 지원자수를 반환하는 메서드
     *
     * @param postId 공고 ID
     * @return 해당 공고의 지원자수
     * @author chocola
     */
    long getNumberOfApplicant(Long postId);
}
