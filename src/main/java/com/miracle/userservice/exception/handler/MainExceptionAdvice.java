package com.miracle.userservice.exception.handler;

import com.miracle.userservice.dto.response.ApiResponse;
import com.miracle.userservice.dto.response.ErrorApiResponse;
import com.sun.jdi.request.InvalidRequestStateException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Objects;

@Slf4j
@RestControllerAdvice
public class MainExceptionAdvice {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ApiResponse invalidRequest(MethodArgumentNotValidException e, BindingResult bindingResult) {
        FieldError fieldError = bindingResult.getFieldError();
        Objects.requireNonNull(fieldError);

        String field = fieldError.getField();
        String[] s = fieldError.getDefaultMessage().split(":");
        String defaultMessage = s[1];
        String code = s[0];
        String exceptionName = e.getClass().getSimpleName();

        log.error("{} : {}", field, defaultMessage);
        return new ErrorApiResponse(HttpStatus.BAD_REQUEST.value(), defaultMessage, code, exceptionName);
    }

    @ExceptionHandler(InvalidRequestStateException.class)
    public ApiResponse invalidToken(InvalidRequestStateException e) {
        log.error(e.getMessage());
        return new ErrorApiResponse(HttpStatus.UNAUTHORIZED.value(), e.getMessage(), "401", e.getClass().getSimpleName());
    }

    @ExceptionHandler(Exception.class)
    public ApiResponse serverError(Exception e) {
        return new ErrorApiResponse(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
                "500",
                e.getClass().getSimpleName());
    }
}