package com.miracle.userservice.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
public class ErrorApiResponse extends CommonApiResponse {

    @Schema(description = "에러 코드")
    private final String code;

    @Schema(description = "예외 클래스 이름")
    private final String exception;

    public ErrorApiResponse(int httpStatus, String message, String code, String exception) {
        super(httpStatus, message);
        this.code = code;
        this.exception = exception;
    }
}
