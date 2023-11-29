package com.miracle.userservice.dto.request;

import com.miracle.userservice.dto.request.validation.util.ValidationDefaultMsgUtil;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.*;
import java.util.List;
import java.util.Set;

@Data
public class ResumePostRequestDto {

    @Positive(message = ValidationDefaultMsgUtil.ResumePost.USER_ID)
    @Schema(
            description = "유저 ID",
            required = true,
            example = "1"
    )
    private final Long userId;

    @Size(max = 50, message = ValidationDefaultMsgUtil.ResumePost.TITLE)
    @NotBlank(message = ValidationDefaultMsgUtil.ResumePost.TITLE)
    @Schema(
            description = "이력서 제목",
            required = true,
            example = "Software Engineer"
    )
    private final String title;

    @Size(max = 20, message = ValidationDefaultMsgUtil.ResumePost.EDUCATION)
    @Schema(
            description = "학력 정보",
            example = "Bachelor's Degree"
    )
    private final String education;

    @Size(max = 100, message = ValidationDefaultMsgUtil.ResumePost.GIT_LINK)
    @Pattern(regexp = "^https://github\\.com/.*$", message = ValidationDefaultMsgUtil.ResumePost.GIT_LINK)
    @Schema(
            description = "GitHub 링크",
            example = "https://github.com/user"
    )
    private final String gitLink;

    @Size(max = 50, message = ValidationDefaultMsgUtil.ResumePost.PHOTO)
    @Schema(
            description = "사진 URL",
            example = "photo-url"
    )
    private final String photo;

    @PositiveOrZero(message = ValidationDefaultMsgUtil.ResumePost.CAREER)
    @Schema(
            description = "경력 연수",
            example = "3"
    )
    private final int career;

    @Schema(
            description = "이력서 공개 여부",
            example = "true"
    )
    private final boolean open;

    @NotNull(message = ValidationDefaultMsgUtil.ResumePost.COLLECTION)
    @Schema(
            description = "기술 스택 ID 목록",
            required = true,
            example = "[1, 3]"
    )
    private final Set<Long> stackIdSet;

    @NotNull(message = ValidationDefaultMsgUtil.ResumePost.COLLECTION)
    @Schema(
            description = "직무 ID 목록",
            required = true,
            example = "[2, 4]"
    )
    private final Set<Long> jobIdSet;

    @NotNull(message = ValidationDefaultMsgUtil.ResumePost.COLLECTION)
    @Schema(
            description = "경력 상세 목록",
            required = true,
            example = "[career1, career2]"
    )
    private final List<String> careerDetailList;

    @NotNull(message = ValidationDefaultMsgUtil.ResumePost.COLLECTION)
    @Schema(
            description = "프로젝트 목록",
            required = true,
            example = "[project1, project2]"
    )
    private final List<String> projectList;

    @NotNull(message = ValidationDefaultMsgUtil.ResumePost.COLLECTION)
    @Schema(
            description = "기타 스킬 목록",
            required = true,
            example = "[etc1, etc2]"
    )
    private final List<String> etcList;

    public ResumePostRequestDto() {
        this.userId = null;
        this.title = null;
        this.education = null;
        this.gitLink = null;
        this.photo = null;
        this.career = 0;
        this.open = false;
        this.stackIdSet = null;
        this.jobIdSet = null;
        this.careerDetailList = null;
        this.projectList = null;
        this.etcList = null;
    }
}
