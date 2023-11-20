package com.miracle.userservice.controller;

import com.miracle.userservice.dto.request.UserLoginRequestDto;
import com.miracle.userservice.dto.response.CommonApiResponse;
import com.miracle.userservice.dto.response.SuccessApiResponse;
import com.miracle.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/user")
public class UserController {

    private final UserService userService;

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/login")
    public CommonApiResponse login(@Valid @RequestBody UserLoginRequestDto dto, HttpServletRequest request, HttpServletResponse response) {
        String sessionId = request.getHeader("sessionId");
        log.debug("sessionId = {}, dto = {}", sessionId, dto);

        boolean login = userService.login(dto);

        String message;
        int httpStatus;
        Boolean data;

        if(login) {
            message = "로그인에 성공했습니다.";
            httpStatus = HttpStatus.OK.value();
            data = null;
            response.setStatus(httpStatus);
        } else {
            message = "로그인에 실패했습니다.";
            httpStatus = HttpStatus.BAD_REQUEST.value();
            data = false;
            response.setStatus(httpStatus);
        }
        return new SuccessApiResponse<>(httpStatus, message, data);
    }
}
