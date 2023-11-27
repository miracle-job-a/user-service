package com.miracle.userservice.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
public class CoverLetterTitleResponseDto {

    @Schema(description = "자기소개서 ID")
    private Long id;

    @Schema(description = "자기소개서 제목")
    private String title;

    public CoverLetterTitleResponseDto(Long id, String title) {
        this.id = id;
        this.title = title;
    }
}