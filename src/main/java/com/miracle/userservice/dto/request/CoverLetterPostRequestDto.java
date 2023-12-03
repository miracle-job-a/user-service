package com.miracle.userservice.dto.request;

import com.miracle.userservice.dto.request.validation.util.ValidationDefaultMsgUtil;
import com.miracle.userservice.entity.Qna;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

@Data
public class CoverLetterPostRequestDto {

    @Size(max = 50, message = ValidationDefaultMsgUtil.CoverLetterPost.TITLE)
    @NotBlank(message = ValidationDefaultMsgUtil.CoverLetterPost.TITLE)
    @Schema(
            description = "제목",
            required = true,
            example = "한 달 만에 채용사이트를 구현할 수 있었던 비결"
    )
    private final String title;

    @NotEmpty(message = ValidationDefaultMsgUtil.CoverLetterPost.QNA)
    @ArraySchema(
            schema = @Schema(
                    description = "질문 및 답변 목록",
                    required = true,
                    implementation = Qna.class
            )
    )
    private final List<Qna> qnaList;

    public CoverLetterPostRequestDto() {
        this.title = null;
        this.qnaList = null;
    }
}