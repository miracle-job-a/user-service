package com.miracle.userservice.exception;

import lombok.Getter;

@Getter
public class MiracleException extends RuntimeException {

    private final String code;

    public MiracleException(String code, String message) {
        super(message);
        this.code = code;
    }
}
