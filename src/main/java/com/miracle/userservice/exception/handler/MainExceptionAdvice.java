package com.miracle.userservice.exception.handler;

import com.miracle.userservice.controller.response.CommonApiResponse;
import com.miracle.userservice.controller.response.ErrorApiResponse;
import com.sun.jdi.request.DuplicateRequestException;
import com.sun.jdi.request.InvalidRequestStateException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.NoSuchElementException;
import java.util.Objects;

@Slf4j
@RestControllerAdvice
public class MainExceptionAdvice {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public CommonApiResponse invalidRequest(MethodArgumentNotValidException e, BindingResult bindingResult) {
        FieldError fieldError = bindingResult.getFieldError();
        Objects.requireNonNull(fieldError);

        String field = fieldError.getField();
        String[] s = fieldError.getDefaultMessage().split(":");
        int httpStatus = HttpStatus.BAD_REQUEST.value();
        String defaultMessage = s[1];
        String code = s[0];
        String exceptionName = getClassSimpleName(e);

        log.error("{} : {}", field, defaultMessage);
        return new ErrorApiResponse(httpStatus, defaultMessage, code, exceptionName);
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(InvalidRequestStateException.class)
    public CommonApiResponse invalidToken(InvalidRequestStateException e) {
        String message = e.getMessage();
        log.error(message);
        int httpStatus = HttpStatus.UNAUTHORIZED.value();
        String code = "401";
        String exceptionName = getClassSimpleName(e);
        return new ErrorApiResponse(httpStatus, message, code, exceptionName);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({NullPointerException.class, DuplicateRequestException.class, NoSuchElementException.class})
    public CommonApiResponse nullPointer(NullPointerException e) {
        String message = e.getMessage();
        log.error(message);
        int httpStatus = HttpStatus.BAD_REQUEST.value();
        String code = "400";
        String exceptionName = getClassSimpleName(e);
        return new ErrorApiResponse(httpStatus, message, code, exceptionName);
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public CommonApiResponse serverError(Exception e) {
        int httpStatus = HttpStatus.INTERNAL_SERVER_ERROR.value();
        log.error(e.getMessage());
        String message = "일시적인 서버 오류입니다.";
        String code = "500";
        String exceptionName = getClassSimpleName(e);
        return new ErrorApiResponse(httpStatus, message, code, exceptionName);
    }

    private String getClassSimpleName(Object obj) {
        return obj.getClass().getSimpleName();
    }
}
