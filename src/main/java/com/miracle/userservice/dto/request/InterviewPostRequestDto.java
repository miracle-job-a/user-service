package com.miracle.userservice.dto.request;

import com.miracle.userservice.dto.request.validation.util.ValidationDefaultMsgUtil;
import com.miracle.userservice.entity.Qna;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.List;

@Data
public class InterviewPostRequestDto {

    @Positive(message = ValidationDefaultMsgUtil.InterviewPost.USER_ID)
    @NotNull(message = ValidationDefaultMsgUtil.InterviewPost.USER_ID)
    @Schema(
            description = "유저 ID",
            required = true,
            example = "1"
    )
    private final Long userId;

    @Positive(message = ValidationDefaultMsgUtil.InterviewPost.APPLICATION_LETTER_ID)
    @NotNull(message = ValidationDefaultMsgUtil.InterviewPost.APPLICATION_LETTER_ID)
    @Schema(
            description = "지원서 ID",
            required = true,
            example = "1"
    )
    private final Long applicationLetterId;

    @NotNull(message = ValidationDefaultMsgUtil.InterviewPost.COLLECTION)
    @ArraySchema(
            schema = @Schema(
                    description = "질문 및 답변 목록",
                    required = true,
                    implementation = Qna.class
            )
    )
    private final List<Qna> qnaList;

    public InterviewPostRequestDto() {
        this.userId = null;
        this.applicationLetterId = null;
        this.qnaList = null;
    }
}