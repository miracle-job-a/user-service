package com.miracle.userservice.exception;

public class DuplicateEmailException extends MiracleException {

    public DuplicateEmailException(String code, String msg) {
        super(code, msg);
    }
}
