package com.miracle.userservice.exception;

public class DuplicateApplicationLetterException extends MiracleException {

    public DuplicateApplicationLetterException(String code, String msg) {
        super(code, msg);
    }
}
