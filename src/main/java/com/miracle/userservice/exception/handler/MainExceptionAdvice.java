package com.miracle.userservice.exception.handler;

import com.miracle.userservice.controller.response.CommonApiResponse;
import com.miracle.userservice.controller.response.ErrorApiResponse;
import com.miracle.userservice.exception.InvalidEmailException;
import com.miracle.userservice.exception.MiracleException;
import com.miracle.userservice.exception.UserIdMismatchException;
import com.sun.jdi.request.InvalidRequestStateException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Objects;

import static org.springframework.http.HttpStatus.*;

@Slf4j
@RestControllerAdvice
public class MainExceptionAdvice {

    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public CommonApiResponse invalidRequest(MethodArgumentNotValidException e, BindingResult bindingResult) {
        FieldError fieldError = bindingResult.getFieldError();
        Objects.requireNonNull(fieldError);

        String[] s = fieldError.getDefaultMessage().split(":");
        int httpStatus = BAD_REQUEST.value();
        String defaultMessage = s[1];
        String code = s[0];
        String exceptionName = getClassSimpleName(e);
        return new ErrorApiResponse(httpStatus, defaultMessage, code, exceptionName);
    }

    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler(InvalidEmailException.class)
    public CommonApiResponse invalidEmail(InvalidEmailException e) {
        int httpStatus = BAD_REQUEST.value();
        String[] split = e.getMessage().split(":");
        String message = split[1];
        String code = split[0];
        String exceptionName = getClassSimpleName(e);
        return new ErrorApiResponse(httpStatus, message, code, exceptionName);
    }

    @ResponseStatus(UNAUTHORIZED)
    @ExceptionHandler(InvalidRequestStateException.class)
    public CommonApiResponse invalidToken(InvalidRequestStateException e) {
        int httpStatus = UNAUTHORIZED.value();
        String message = e.getMessage();
        String code = "401";
        String exceptionName = getClassSimpleName(e);
        return new ErrorApiResponse(httpStatus, message, code, exceptionName);
    }

    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler(MiracleException.class)
    public CommonApiResponse miracleError(MiracleException e) {
        String message = e.getMessage();
        log.error(message);
        int httpStatus = BAD_REQUEST.value();
        String code = e.getCode();
        String exceptionName = getClassSimpleName(e);
        return new ErrorApiResponse(httpStatus, message, code, exceptionName);
    }

    @ResponseStatus(INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public CommonApiResponse serverError(Exception e) {
        int httpStatus = INTERNAL_SERVER_ERROR.value();
        log.error(e.getMessage());
        String message = "일시적인 서버 오류입니다.";
        String code = "500";
        String exceptionName = getClassSimpleName(e);
        return new ErrorApiResponse(httpStatus, message, code, exceptionName);
    }

    @ResponseStatus(FORBIDDEN)
    @ExceptionHandler(UserIdMismatchException.class)
    public CommonApiResponse userIdMismatch(UserIdMismatchException e) {
        String message = e.getMessage();
        log.error(message);
        int httpStatus = FORBIDDEN.value();
        String code = "403";
        String exceptionName = getClassSimpleName(e);
        return new ErrorApiResponse(httpStatus, message, code, exceptionName);
    }

    private String getClassSimpleName(Object obj) {
        return obj.getClass().getSimpleName();
    }
}
