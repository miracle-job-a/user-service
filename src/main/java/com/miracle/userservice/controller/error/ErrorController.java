package com.miracle.userservice.controller.error;

import com.miracle.userservice.exception.InvalidTokenException;
import com.sun.jdi.request.InvalidRequestStateException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequestMapping("/error")
@RestController
public class ErrorController {

    @GetMapping("/invalid-request")
    public void invalidRequest() {
        throw new InvalidRequestStateException("올바르지 않은 요청입니다.");
    }

    @GetMapping("/invalid-token")
    public void invalidToken() {
        throw new InvalidTokenException("토큰 인증 실패");
    }
}
