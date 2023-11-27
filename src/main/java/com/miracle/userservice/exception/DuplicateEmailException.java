package com.miracle.userservice.exception;

public class DuplicateEmailException extends MiracleException {

    public DuplicateEmailException(String msg, String code) {
        super(msg, code);
    }
}
