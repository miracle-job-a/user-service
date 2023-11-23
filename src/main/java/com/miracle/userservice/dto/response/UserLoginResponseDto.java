package com.miracle.userservice.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
public final class UserLoginResponseDto {

    @Schema(description = "로그인 성공 여부")
    private final boolean success;

    @Schema(description = "유저 아이디")
    private final Long id;

    @Schema(description = "유저 이메일")
    private final String email;

    @Schema(description = "유저 이름")
    private final String name;

    @Builder
    public UserLoginResponseDto(boolean success, Long id, String email, String name) {
        this.success = success;
        this.id = id;
        this.email = email;
        this.name = name;
    }
}
