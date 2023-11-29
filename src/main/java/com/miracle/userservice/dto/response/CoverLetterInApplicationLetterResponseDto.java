package com.miracle.userservice.dto.response;

import com.miracle.userservice.entity.Qna;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
public class CoverLetterInApplicationLetterResponseDto {
    private final Long id;
    private final String coverLetterTitle;
    private final List<Qna> qnaList;

    @Builder
    public CoverLetterInApplicationLetterResponseDto(Long id, String coverLetterTitle, List<Qna> qnaList) {
        this.id = id;
        this.coverLetterTitle = coverLetterTitle;
        this.qnaList = List.copyOf(qnaList);
    }
}