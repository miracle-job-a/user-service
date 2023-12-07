package com.miracle.userservice.dto.request;

import com.miracle.userservice.dto.request.validation.util.ValidationDefaultMsgUtil;
import com.miracle.userservice.entity.ApplicationStatus;
import com.miracle.userservice.entity.PostType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.*;
import java.time.LocalDateTime;

@Data
public class ApplicationLetterPostRequestDto {

    @Positive(message = ValidationDefaultMsgUtil.ApplicationLetterPost.RESUME_ID)
    @Schema(
            description = "이력서 ID",
            required = true,
            example = "1"
    )
    private final Long resumeId;

    @Positive(message = ValidationDefaultMsgUtil.ApplicationLetterPost.COVER_LETTER_ID)
    @Schema(
            description = "자기소개서 ID",
            required = true,
            example = "1"
    )
    private final Long coverLetterId;

    @NotNull(message = ValidationDefaultMsgUtil.ApplicationLetterPost.POST_TYPE)
    @Schema(
            description = "공고 타입, 2개의 값 중 선택해야함(NORMAL, MZ)",
            required = true,
            example = "MZ"
    )
    private final PostType postType;

    @Positive(message = ValidationDefaultMsgUtil.ApplicationLetterPost.POST_ID)
    @Schema(
            description = "공고 ID",
            required = true,
            example = "1"
    )
    private final Long postId;

    @NotNull(message = ValidationDefaultMsgUtil.ApplicationLetterPost.SUBMIT_DATE)
    @Schema(
            description = "지원 일자 'yyyy-MM-ddTHH-mm-ss' 형식으로 요청해야함",
            required = true,
            example = "2023-11-28T19:19:20"
    )
    private final LocalDateTime submitDate;

    @NotNull(message = ValidationDefaultMsgUtil.ApplicationLetterPost.APPLICATION_STATUS)
    @Schema(
            description = "지원 상태, 3개의 값 중 선택해야함(PASS, FAIL, IN_PROGRESS)",
            required = true,
            example = "PASS"
    )
    private final ApplicationStatus applicationStatus;

    @Size(max = 50, message = ValidationDefaultMsgUtil.ApplicationLetterPost.USER_JOB)
    @Schema(
            description = "유저 직무",
            example = "Backend Engineer"
    )
    private final String userJob;

    public ApplicationLetterPostRequestDto() {
        this.resumeId = null;
        this.coverLetterId = null;
        this.postType = null;
        this.postId = null;
        this.submitDate = null;
        this.applicationStatus = null;
        this.userJob = null;
    }

    @Builder
    public ApplicationLetterPostRequestDto(Long resumeId, Long coverLetterId, PostType postType, Long postId, LocalDateTime submitDate, ApplicationStatus applicationStatus, String userJob) {
        this.resumeId = resumeId;
        this.coverLetterId = coverLetterId;
        this.postType = postType;
        this.postId = postId;
        this.submitDate = submitDate;
        this.applicationStatus = applicationStatus;
        this.userJob = userJob;
    }
}
