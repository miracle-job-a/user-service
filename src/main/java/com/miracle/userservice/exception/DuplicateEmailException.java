package com.miracle.userservice.exception;

import com.sun.jdi.request.DuplicateRequestException;

public class DuplicateEmailException extends DuplicateRequestException {

    public DuplicateEmailException(String msg) {
        super(msg);
    }
}
