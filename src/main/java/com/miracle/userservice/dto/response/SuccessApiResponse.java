package com.miracle.userservice.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
public class SuccessApiResponse<T> extends CommonApiResponse {

    private final T data;

    @Builder
    public SuccessApiResponse(int httpStatus, String message, T data) {
        super(httpStatus, message);
        this.data = data;
    }
}