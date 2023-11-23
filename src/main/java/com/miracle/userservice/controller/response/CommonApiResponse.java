package com.miracle.userservice.controller.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
public class CommonApiResponse {

    @Schema(description = "응답 상태 코드")
    private final int httpStatus;

    @Schema(description = "응답 메시지")
    private final String message;

    public CommonApiResponse(int httpStatus, String message) {
        this.httpStatus = httpStatus;
        this.message = message;
    }
}
