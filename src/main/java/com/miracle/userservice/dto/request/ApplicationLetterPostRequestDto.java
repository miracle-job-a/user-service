package com.miracle.userservice.dto.request;

import com.miracle.userservice.dto.request.validation.util.ValidationDefaultMsgUtil;
import com.miracle.userservice.entity.ApplicationStatus;
import com.miracle.userservice.entity.PostType;
import com.miracle.userservice.entity.Qna;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Data
public class ApplicationLetterPostRequestDto {

    @Enumerated(EnumType.STRING)
    @NotNull(message = ValidationDefaultMsgUtil.ApplicationLetterPost.POST_TYPE)
    @Schema(
            description = "공고 타입",
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
            description = "지원 일자, 'yyyy-MM-dd' 형식으로 요청해야함",
            required = true,
            example = "2023-11-28"
    )
    private final LocalDateTime submitDate;

    @Enumerated(EnumType.STRING)
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
        this.postType = null;
        this.postId = null;
        this.submitDate = null;
        this.applicationStatus = null;
        this.userJob = null;
    }

    @Builder
    public ApplicationLetterPostRequestDto(PostType postType, Long postId, LocalDateTime submitDate, ApplicationStatus applicationStatus, String resumeTitle, String coverLetterTitle, String userEmail, String userName, String userPhone, String userEducation, String userJob, String userGitLink, LocalDate userBirth, String userAddress, int userCareer, List<String> careerDetailList, List<String> projectList, List<String> etcList, List<Qna> qnaList, Set<Long> stackIdSet) {
        this.postType = postType;
        this.postId = postId;
        this.submitDate = submitDate;
        this.applicationStatus = applicationStatus;
        this.userJob = userJob;
    }
}
