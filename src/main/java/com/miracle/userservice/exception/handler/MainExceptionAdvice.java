package com.miracle.userservice.exception.handler;

import com.miracle.userservice.dto.response.CommonApiResponse;
import com.miracle.userservice.dto.response.ErrorApiResponse;
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

        String field = fieldError.getField();
        String[] s = fieldError.getDefaultMessage().split(":");
        String defaultMessage = s[1];
        String code = s[0];
        String exceptionName = e.getClass().getSimpleName();

        log.error("{} : {}", field, defaultMessage);
        return new ErrorApiResponse(HttpStatus.BAD_REQUEST.value(), defaultMessage, code, exceptionName);
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(InvalidRequestStateException.class)
    public CommonApiResponse invalidToken(InvalidRequestStateException e) {
        log.error(e.getMessage());
        return new ErrorApiResponse(HttpStatus.UNAUTHORIZED.value(), e.getMessage(), "401", e.getClass().getSimpleName());
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public CommonApiResponse serverError(Exception e) {
        return new ErrorApiResponse(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
                "500",
                e.getClass().getSimpleName());
    }
}
