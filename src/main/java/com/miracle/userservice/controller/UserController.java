package com.miracle.userservice.controller;

import com.miracle.userservice.dto.response.ApiResponse;
import com.miracle.userservice.dto.response.SuccessApiResponse;
import com.miracle.userservice.dto.request.UserLoginRequestDto;
import com.miracle.userservice.entity.User;
import com.miracle.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/user")
public class UserController {

    private final UserService userService;

    @PostMapping("/login")
    public ApiResponse login(@Valid @RequestBody UserLoginRequestDto dto, HttpServletRequest request) {
        log.debug("dto = {}", dto);
        userService.login(dto);

        String message = "로그인에 성공했습니다.";
        return new SuccessApiResponse<>(HttpStatus.OK.value(), message, null);
    }
}
