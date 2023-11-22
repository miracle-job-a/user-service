package com.miracle.userservice.exception.handler;

import com.miracle.userservice.dto.response.CommonApiResponse;
import com.miracle.userservice.dto.response.ErrorApiResponse;
import com.miracle.userservice.exception.DuplicateEmailException;
import com.miracle.userservice.exception.InvalidEmailException;
import com.sun.jdi.request.InvalidRequestStateException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Objects;

@Slf4j
@RestControllerAdvice
public class MainExceptionAdvice {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public CommonApiResponse invalidRequest(MethodArgumentNotValidException e, BindingResult bindingResult) {
        FieldError fieldError = bindingResult.getFieldError();
        Objects.requireNonNull(fieldError);

        String[] s = fieldError.getDefaultMessage().split(":");
        int httpStatus = HttpStatus.BAD_REQUEST.value();
        String defaultMessage = s[1];
        String code = s[0];
        String exceptionName = getClassSimpleName(e);
        return new ErrorApiResponse(httpStatus, defaultMessage, code, exceptionName);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(InvalidEmailException.class)
    public CommonApiResponse invalidEmail(InvalidEmailException e) {
        int httpStatus = HttpStatus.BAD_REQUEST.value();
        String[] split = e.getMessage().split(":");
        String message = split[1];
        String code = split[0];
        String exceptionName = getClassSimpleName(e);
        return new ErrorApiResponse(httpStatus, message, code, exceptionName);
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(InvalidRequestStateException.class)
    public CommonApiResponse invalidToken(InvalidRequestStateException e) {
        int httpStatus = HttpStatus.UNAUTHORIZED.value();
        String message = e.getMessage();
        String code = "401";
        String exceptionName = getClassSimpleName(e);
        return new ErrorApiResponse(httpStatus, message, code, exceptionName);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(DuplicateEmailException.class)
    public CommonApiResponse duplicateEmail(DuplicateEmailException e) {
        int httpStatus = HttpStatus.BAD_REQUEST.value();
        String message = e.getMessage();
        String code = "400";
        String exceptionName = getClassSimpleName(e);
        return new ErrorApiResponse(httpStatus, message, code, exceptionName);
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public CommonApiResponse serverError(Exception e) {
        int httpStatus = HttpStatus.INTERNAL_SERVER_ERROR.value();
        String message = e.getMessage();
        String code = "500";
        String exceptionName = getClassSimpleName(e);
        return new ErrorApiResponse(httpStatus, message, code, exceptionName);
    }

    private String getClassSimpleName(Object obj) {
        return obj.getClass().getSimpleName();
    }
}
