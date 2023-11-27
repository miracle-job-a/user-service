package com.miracle.userservice.controller;

import com.miracle.userservice.controller.response.CommonApiResponse;
import com.miracle.userservice.controller.response.SuccessApiResponse;
import com.miracle.userservice.dto.request.ResumePostRequestDto;
import com.miracle.userservice.dto.response.ResumeResponseDto;
import com.miracle.userservice.service.ResumeService;
import com.miracle.userservice.swagger.ApiDeleteResume;
import com.miracle.userservice.swagger.ApiGetResume;
import com.miracle.userservice.swagger.ApiPostResume;
import com.miracle.userservice.swagger.ApiPutResume;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/v1/user/{userId}/resume")
@RestController
public class ResumeController {

    private final ResumeService resumeService;

    @ApiGetResume
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{resumeId}")
    public CommonApiResponse getResume(@PathVariable Long id, @RequestParam Requester requester) {
        ResumeResponseDto dto = resumeService.getResumeDetail(id, requester);
        int httpStatus = HttpStatus.OK.value();
        String message = "이력서 조회 성공";
        return new SuccessApiResponse<>(httpStatus, message, dto);
    }

    @ApiPostResume
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public CommonApiResponse postResume(@RequestBody @Valid ResumePostRequestDto dto) {
        boolean success = resumeService.postResume(dto);
        int httpStatus = HttpStatus.OK.value();
        String message = "이력서 저장 성공";
        return new SuccessApiResponse<>(httpStatus, message, success);
    }

    @ApiPutResume
    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/{resumeId}")
    public CommonApiResponse updateResume(@PathVariable Long id, @RequestBody @Valid ResumePostRequestDto dto) {
        boolean success = resumeService.updateResume(id, dto);
        int httpStatus = HttpStatus.OK.value();
        String message = "이력서 수정 성공";
        return new SuccessApiResponse<>(httpStatus, message, success);
    }

    @ApiDeleteResume
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/{resumeId}")
    public CommonApiResponse deleteResume(@PathVariable Long id) {
        boolean success = resumeService.deleteResume(id);
        int httpStatus = HttpStatus.OK.value();
        String message = "이력서 삭제 성공";
        return new SuccessApiResponse<>(httpStatus, message, success);
    }
}
