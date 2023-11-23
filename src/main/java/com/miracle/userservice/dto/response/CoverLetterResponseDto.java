package com.miracle.userservice.dto.response;

import com.miracle.userservice.entity.Qna;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Data
public class CoverLetterResponseDto {
    private final Long id;
    private final String title;
    private final String modifiedAt;
    private final List<Qna> qnaList;

    @Builder
    public CoverLetterResponseDto(Long id, String title, LocalDateTime modifiedAt, List<Qna> qnaList) {
        this.id = id;
        this.title = title;
        this.modifiedAt = formatDateTime(modifiedAt);
        this.qnaList = List.copyOf(qnaList);
    }

    private String formatDateTime(LocalDateTime dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return dateTime.format(formatter);
    }
}
