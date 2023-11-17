package com.miracle.userservice.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
public class SuccessApiResponse<T> extends CommonApiResponse {

    @Schema(description = "응답 데이터")
    private final T data;

    public SuccessApiResponse(int httpStatus, String message, T data) {
        super(httpStatus, message);
        this.data = data;
    }
}
