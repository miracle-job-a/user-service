package com.miracle.userservice.exception;

import com.sun.jdi.request.InvalidRequestStateException;

public class UserIdMismatchException extends InvalidRequestStateException {

    public UserIdMismatchException(String msg) {
        super(msg);
    }
}
