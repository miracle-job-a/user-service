package com.miracle.userservice.dto.response;

import com.miracle.userservice.entity.ApplicationStatus;
import com.miracle.userservice.entity.PostType;
import com.miracle.userservice.util.DateFormatUtil;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ApplicationLetterListResponseDto {

    private final Long applicationLetterId;
    private final Long postId;
    private final Long interviewId;
    private final String postType;
    private final String submitDate;
    private final String applicationStatus;
    private final String job;

    @Builder
    public ApplicationLetterListResponseDto(Long applicationLetterId, Long postId, Long interviewId, PostType postType, LocalDateTime submitDate, ApplicationStatus applicationStatus, String job) {
        this.applicationLetterId = applicationLetterId;
        this.postId = postId;
        this.interviewId = interviewId;
        this.postType = postType.name();
        this.submitDate = DateFormatUtil.dateToString(submitDate);
        this.applicationStatus = applicationStatus.name();
        this.job = job;
    }
}
