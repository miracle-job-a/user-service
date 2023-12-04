package com.miracle.userservice.dto.response;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserJoinListResponseDto {

    private final Long userId;
    private final String email;
    private final String name;
    private final LocalDateTime createdAt;

    @Builder
    public UserJoinListResponseDto(Long userId, String email, String name, LocalDateTime createdAt) {
        this.userId = userId;
        this.email = email;
        this.name = name;
        this.createdAt = createdAt;
    }
}
