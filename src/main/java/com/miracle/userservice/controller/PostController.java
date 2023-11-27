package com.miracle.userservice.controller;

import com.miracle.userservice.controller.response.CommonApiResponse;
import com.miracle.userservice.controller.response.SuccessApiResponse;
import com.miracle.userservice.service.ApplicationLetterService;
import com.miracle.userservice.swagger.DefaultPathDocket;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@DefaultPathDocket
@RequiredArgsConstructor
@RequestMapping("/v1/post/{postId}")
@RestController
public class PostController {

    private final ApplicationLetterService applicationLetterService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/applicant")
    public CommonApiResponse getNumberOfApplicant(@PathVariable Long postId) {
        long count = applicationLetterService.getNumberOfApplicant(postId);
        int httpStatus = HttpStatus.OK.value();
        String message = "지원자수 응답 성공";
        return new SuccessApiResponse<>(httpStatus, message, count);
    }
}
