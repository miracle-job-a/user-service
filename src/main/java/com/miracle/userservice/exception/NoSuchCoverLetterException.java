package com.miracle.userservice.exception;

import java.util.NoSuchElementException;

public class NoSuchCoverLetterException extends NoSuchElementException {
    public NoSuchCoverLetterException(String msg) {
        super(msg);
    }
}
