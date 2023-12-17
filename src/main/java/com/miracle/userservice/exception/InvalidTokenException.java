package com.miracle.userservice.exception;

import com.sun.jdi.request.InvalidRequestStateException;

public class InvalidTokenException extends InvalidRequestStateException {

    public InvalidTokenException(String msg) {
        super(msg);
    }
}
