package com.miracle.userservice.controller;

import com.miracle.userservice.controller.response.CommonApiResponse;
import com.miracle.userservice.controller.response.SuccessApiResponse;
import com.miracle.userservice.dto.request.ResumePostRequestDto;
import com.miracle.userservice.dto.response.ResumeListResponseDto;
import com.miracle.userservice.dto.response.ResumeResponseDto;
import com.miracle.userservice.service.ResumeService;
import com.miracle.userservice.swagger.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@UserPathDocket
@RequiredArgsConstructor
@RequestMapping("/v1/user/{userId}/resume")
@RestController
public class ResumeController {

    private final ResumeService resumeService;

    @ApiGetResumes
    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public CommonApiResponse getResumes(@PathVariable Long userId) {
        List<ResumeListResponseDto> resumeList = resumeService.getUserResumes(userId);
        int httpStatus = HttpStatus.OK.value();
        String message = "이력서 조회 성공";
        return new SuccessApiResponse<>(httpStatus, message, resumeList);
    }

    @ApiGetResume
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{resumeId}")
    public CommonApiResponse getResume(@PathVariable Long resumeId, @RequestParam Requester requester) {
        ResumeResponseDto dto = resumeService.getResumeDetail(resumeId, requester);
        int httpStatus = HttpStatus.OK.value();
        String message = "이력서 조회 성공";
        return new SuccessApiResponse<>(httpStatus, message, dto);
    }

    @ApiPostResume
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public CommonApiResponse postResume(@PathVariable Long userId, @RequestBody @Valid ResumePostRequestDto dto) {
        boolean success = resumeService.postResume(userId, dto);
        int httpStatus = HttpStatus.CREATED.value();
        String message = "이력서 저장 성공";
        return new SuccessApiResponse<>(httpStatus, message, success);
    }

    @ApiPutResume
    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/{resumeId}")
    public CommonApiResponse updateResume(@PathVariable Long resumeId, @RequestBody @Valid ResumePostRequestDto dto) {
        boolean success = resumeService.updateResume(resumeId, dto);
        int httpStatus = HttpStatus.OK.value();
        String message = "이력서 수정 성공";
        return new SuccessApiResponse<>(httpStatus, message, success);
    }

    @ApiDeleteResume
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/{resumeId}")
    public CommonApiResponse deleteResume(@PathVariable Long resumeId) {
        boolean success = resumeService.deleteResume(resumeId);
        int httpStatus = HttpStatus.OK.value();
        String message = "이력서 삭제 성공";
        return new SuccessApiResponse<>(httpStatus, message, success);
    }
}
