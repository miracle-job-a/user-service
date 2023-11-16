package com.miracle.userservice.exception;

import com.miracle.userservice.dto.response.ApiResponse;
import com.miracle.userservice.dto.response.ErrorApiResponse;
import com.sun.jdi.request.InvalidRequestStateException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class UserLoginExceptionAdvice {

    @ExceptionHandler(NoSuchEmailException.class)
    public ApiResponse noSuchEmailException() {
        log.info("NoSuchEmailException ERROR 발생!");
        return ErrorApiResponse.builder()
                .httpStatus(HttpStatus.BAD_REQUEST.value())
                .message("이메일 정보가 없습니다.")
                .code("400_1")
                .exception("NoSuchEmailException")
                .build();
    }

    @ExceptionHandler(PasswordMismatchException.class)
    public ApiResponse passwordMismatchException() {
        log.info("PasswordMismatchException ERROR 발생!");
        return ErrorApiResponse.builder()
                .httpStatus(HttpStatus.BAD_REQUEST.value())
                .message("비밀번호가 일치하지 않습니다.")
                .code("400_2")
                .exception("PasswordMismachException")
                .build();
    }

    @ExceptionHandler(InvalidRequestStateException.class)
    public ApiResponse invalidRequestStateException() {
        log.info("InvalidRequestStateException ERROR 발생!");
        return ErrorApiResponse.builder()
                .httpStatus(HttpStatus.UNAUTHORIZED.value())
                .message("올바르지 않은 요청입니다.")
                .code("401")
                .exception("InvalidRequestStateException")
                .build();
    }
}
