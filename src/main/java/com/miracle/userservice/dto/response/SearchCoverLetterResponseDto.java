package com.miracle.userservice.dto.response;

import lombok.Data;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
public class SearchCoverLetterResponseDto {

    private final Long id;
    private final Long userId;
    private final String title;
    private final String modifiedAt;

    public SearchCoverLetterResponseDto(Long id, Long userId, String title, LocalDateTime modifiedAt) {
        this.id = id;
        this.userId = userId;
        this.title = title;
        this.modifiedAt = formatDateTime(modifiedAt);
    }

    private String formatDateTime(LocalDateTime dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return dateTime.format(formatter);
    }
}
