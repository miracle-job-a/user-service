package com.miracle.userservice.controller;

import com.miracle.userservice.dto.request.UserJoinRequestDto;
import com.miracle.userservice.dto.response.ApiResponse;
import com.miracle.userservice.dto.response.SuccessApiResponse;
import com.miracle.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/v1/user")
@RestController
public class UserController {

    private final UserService userService;

    @PostMapping("/join")
    public ApiResponse join(@Valid @RequestBody UserJoinRequestDto dto, HttpServletRequest request) {
        log.debug("dto={}", dto);

        userService.join(dto);

        String message = "회원가입 성공";
        return new SuccessApiResponse<>(HttpStatus.OK.value(), message, null);
    }
}
