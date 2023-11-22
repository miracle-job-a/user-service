package com.miracle.userservice.controller;

import com.miracle.userservice.controller.response.CommonApiResponse;
import com.miracle.userservice.controller.response.SuccessApiResponse;
import com.miracle.userservice.dto.response.ResumeResponseDto;
import com.miracle.userservice.service.ResumeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/v1/resume")
@RestController
public class ResumeController {

    private final ResumeService resumeService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public CommonApiResponse getResume(@PathVariable Long id) {
        ResumeResponseDto dto = resumeService.getResumeDetail(id);
        int httpStatus = HttpStatus.OK.value();
        String message = "이력서 조회 성공";
        return new SuccessApiResponse<>(httpStatus, message, dto);
    }
}
