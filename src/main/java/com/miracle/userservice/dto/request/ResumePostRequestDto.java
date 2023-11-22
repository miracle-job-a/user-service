package com.miracle.userservice.dto.request;

import com.miracle.userservice.dto.request.validation.util.ValidationDefaultMsgUtil;
import lombok.Data;

import javax.validation.constraints.*;
import java.util.List;
import java.util.Set;

@Data
public class ResumePostRequestDto {

    @Positive(message = ValidationDefaultMsgUtil.ResumePost.USER_ID)
    private final Long userId;

    @Size(max = 50, message = ValidationDefaultMsgUtil.ResumePost.TITLE)
    @NotBlank(message = ValidationDefaultMsgUtil.ResumePost.TITLE)
    private final String title;

    @Size(max = 20, message = ValidationDefaultMsgUtil.ResumePost.EDUCATION)
    private final String education;

    @Size(max = 100, message = ValidationDefaultMsgUtil.ResumePost.GIT_LINK)
    private final String gitLink;

    @Size(max = 50, message = ValidationDefaultMsgUtil.ResumePost.PHOTO)
    private final String photo;

    @PositiveOrZero(message = ValidationDefaultMsgUtil.ResumePost.CAREER)
    private final int career;

    private final boolean open;

    @NotNull(message = ValidationDefaultMsgUtil.ResumePost.COLLECTION)
    private final Set<Long> stackIdSet;

    @NotNull(message = ValidationDefaultMsgUtil.ResumePost.COLLECTION)
    private final Set<Long> jobIdSet;

    @NotNull(message = ValidationDefaultMsgUtil.ResumePost.COLLECTION)
    private final List<String> careerDetailList;

    @NotNull(message = ValidationDefaultMsgUtil.ResumePost.COLLECTION)
    private final List<String> projectList;

    @NotNull(message = ValidationDefaultMsgUtil.ResumePost.COLLECTION)
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
