package com.miracle.userservice.exception;

import com.sun.jdi.request.InvalidRequestStateException;

public class InvalidEmailException extends InvalidRequestStateException {

    public InvalidEmailException(String msg) {
        super(msg);
    }
}
