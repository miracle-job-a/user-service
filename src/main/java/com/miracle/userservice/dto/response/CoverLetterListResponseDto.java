package com.miracle.userservice.dto.response;

import com.miracle.userservice.util.DateFormatUtil;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CoverLetterListResponseDto {

    private final Long id;
    private final String title;
    private final String modifiedAt;

    public CoverLetterListResponseDto(Long id, String title, LocalDateTime modifiedAt) {
        this.id = id;
        this.title = title;
        this.modifiedAt = DateFormatUtil.dateToString(modifiedAt);
    }
}
