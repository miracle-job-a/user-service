package com.miracle.userservice.controller;

import com.miracle.userservice.controller.response.CommonApiResponse;
import com.miracle.userservice.controller.response.SuccessApiResponse;
import com.miracle.userservice.dto.response.InterviewResponseDto;
import com.miracle.userservice.service.InterviewService;
import com.miracle.userservice.swagger.ApiDeleteInterview;
import com.miracle.userservice.swagger.ApiGetInterviews;
import com.miracle.userservice.swagger.UserPathDocket;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@UserPathDocket
@RequiredArgsConstructor
@RequestMapping("/v1/user/{userId}/interview")
@RestController
public class InterviewController {

    private final InterviewService interviewService;

    @ApiGetInterviews
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{interviewId}")
    public CommonApiResponse getInterviews(@PathVariable Long interviewId) {
        InterviewResponseDto dto = interviewService.getInterviews(interviewId);

        int httpStatus = HttpStatus.OK.value();
        String message = "면접 정보 조회 성공";
        return new SuccessApiResponse<>(httpStatus, message, dto);
    }

    @ApiDeleteInterview
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/{interviewId}")
    public CommonApiResponse deleteInterview(@PathVariable Long interviewId) {
        boolean result = interviewService.deleteInterview(interviewId);

        int httpStatus = HttpStatus.OK.value();
        String message = "면접 정보 삭제 성공";
        return new SuccessApiResponse<>(httpStatus, message, result);
    }
}
