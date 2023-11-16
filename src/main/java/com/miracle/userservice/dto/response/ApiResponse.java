package com.miracle.userservice.dto.response;

import lombok.Getter;

@Getter
public class ApiResponse {

    private final int httpStatus;
    private final String message;

    public ApiResponse(int httpStatus, String message) {
        this.httpStatus = httpStatus;
        this.message = message;
    }
}
