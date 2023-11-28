package com.miracle.userservice.exception;

public class NoSuchUserException extends MiracleException {

    public NoSuchUserException(String code, String msg) {
        super(code, msg);
    }
}
