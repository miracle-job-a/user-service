package com.miracle.userservice.controller;

import com.miracle.userservice.controller.response.CommonApiResponse;
import com.miracle.userservice.controller.response.SuccessApiResponse;
import com.miracle.userservice.dto.request.ResumePostRequestDto;
import com.miracle.userservice.dto.response.ResumeResponseDto;
import com.miracle.userservice.service.ResumeService;
import com.miracle.userservice.swagger.ApiResumeDelete;
import com.miracle.userservice.swagger.ApiResumeRead;
import com.miracle.userservice.swagger.ApiResumeCreate;
import com.miracle.userservice.swagger.ApiResumeUpdate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/v1/user/{id}/resume")
@RestController
public class ResumeController {

    private final ResumeService resumeService;

    @ApiResumeRead
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public CommonApiResponse getResume(@PathVariable Long id, @RequestParam Requester requester) {
        ResumeResponseDto dto = resumeService.getResumeDetail(id, requester);
        int httpStatus = HttpStatus.OK.value();
        String message = "이력서 조회 성공";
        return new SuccessApiResponse<>(httpStatus, message, dto);
    }

    @ApiResumeCreate
    @ResponseStatus(HttpStatus.OK)
    @PostMapping
    public CommonApiResponse postResume(@RequestBody @Valid ResumePostRequestDto dto) {
        boolean success = resumeService.postResume(dto);
        int httpStatus = HttpStatus.OK.value();
        String message = "이력서 저장 성공";
        return new SuccessApiResponse<>(httpStatus, message, success);
    }

    @ApiResumeUpdate
    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/{id}")
    public CommonApiResponse updateResume(@PathVariable Long id, @RequestBody @Valid ResumePostRequestDto dto) {
        boolean success = resumeService.updateResume(id, dto);
        int httpStatus = HttpStatus.OK.value();
        String message = "이력서 수정 성공";
        return new SuccessApiResponse<>(httpStatus, message, success);
    }

    @ApiResumeDelete
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/{id}")
    public CommonApiResponse deleteResume(@PathVariable Long id) {
        boolean success = resumeService.deleteResume(id);
        int httpStatus = HttpStatus.OK.value();
        String message = "이력서 삭제 성공";
        return new SuccessApiResponse<>(httpStatus, message, success);
    }
}
