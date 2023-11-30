package com.miracle.userservice.exception;

public class InvalidParameterException extends MiracleException {

    public InvalidParameterException(String code, String message) {
        super(code, message);
    }
}
