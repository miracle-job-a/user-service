package com.miracle.userservice.dto.response;

import lombok.Data;

import java.util.List;

@Data
public class ApplicationLetterResponseDto {

    private final List<ResumeTitleResponseDto> resumeList;
    private final List<CoverLetterTitleResponseDto> coverLetterList;

    public ApplicationLetterResponseDto(List<ResumeTitleResponseDto> resumeList, List<CoverLetterTitleResponseDto> coverLetterList) {
        this.resumeList = resumeList;
        this.coverLetterList = coverLetterList;
    }
}