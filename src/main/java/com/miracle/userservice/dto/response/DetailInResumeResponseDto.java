package com.miracle.userservice.dto.response;

import lombok.Data;

@Data
public class DetailInResumeResponseDto {

    private final Long id;
    private final String content;

    public DetailInResumeResponseDto(Long id, String content) {
        this.id = id;
        this.content = content;
    }
}
