package com.miracle.userservice.controller;

import com.miracle.userservice.controller.swagger.ApiUserJoin;
import com.miracle.userservice.dto.request.UserJoinRequestDto;
import com.miracle.userservice.dto.response.CommonApiResponse;
import com.miracle.userservice.dto.response.SuccessApiResponse;
import com.miracle.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/v1/user")
@RestController
public class UserController {

    private final UserService userService;

    @ApiUserJoin
    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/join")
    public CommonApiResponse join(@Valid @RequestBody UserJoinRequestDto dto) {
        log.debug("dto={}", dto);

        userService.join(dto);

        String message = "회원 가입 성공";
        return new SuccessApiResponse<>(HttpStatus.OK.value(), message, null);
    }
}
