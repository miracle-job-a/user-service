package com.miracle.userservice.exception;

public class NoSuchUserException extends MiracleException {

    public NoSuchUserException(String msg, String code) {
        super(msg, code);
    }
}
