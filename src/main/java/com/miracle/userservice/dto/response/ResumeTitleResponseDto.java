package com.miracle.userservice.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
public class ResumeTitleResponseDto {

    @Schema(description = "이력서 ID")
    private Long id;

    @Schema(description = "이력서 제목")
    private String title;

    public ResumeTitleResponseDto(Long id, String title) {
        this.id = id;
        this.title = title;
    }
}
