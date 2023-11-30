package com.miracle.userservice.controller;

import com.miracle.userservice.controller.response.CommonApiResponse;
import com.miracle.userservice.controller.response.SuccessApiResponse;
import com.miracle.userservice.dto.request.ApplicationLetterPostRequestDto;
import com.miracle.userservice.dto.response.ApplicationLetterResponseDto;
import com.miracle.userservice.dto.response.CoverLetterInApplicationLetterResponseDto;
import com.miracle.userservice.dto.response.ResumeInApplicationLetterResponseDto;
import com.miracle.userservice.service.ApplicationLetterService;
import com.miracle.userservice.swagger.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@UserPathDocket
@RequiredArgsConstructor
@RequestMapping("/v1/user/{userId}/application-letter")
@RestController
public class ApplicationLetterController {

    private final ApplicationLetterService applicationLetterService;

    @ApiGetResumeAndCoverLetterList
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/popup")
    public CommonApiResponse getResumeAndCoverLetterList(@PathVariable Long userId) {
        ApplicationLetterResponseDto dto = applicationLetterService.getResumeAndCoverLetterList(userId);

        int httpStatus = HttpStatus.OK.value();
        String message = "이력서 및 자기소개서 목록 출력 성공";

        return new SuccessApiResponse<>(httpStatus, message, dto);
    }

    @ApiPostApplicationLetter
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public CommonApiResponse postApplicationLetter(@PathVariable Long userId,
                                                   @RequestParam Long resumeId,
                                                   @RequestParam Long coverLetterId,
                                                   @Valid @RequestBody ApplicationLetterPostRequestDto dto) {
        boolean result = applicationLetterService.postApplicationLetter(userId, resumeId, coverLetterId, dto);

        int httpStatus = HttpStatus.OK.value();
        String message = "지원서 등록 성공";

        return new SuccessApiResponse<>(httpStatus, message, result);
    }

    @ApiGetResumeInApplicationLetter
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{applicationLetterId}/resume")
    public CommonApiResponse getResume(@PathVariable Long applicationLetterId) {
        ResumeInApplicationLetterResponseDto dto = applicationLetterService.getResume(applicationLetterId);

        int httpStatus = HttpStatus.OK.value();
        String message = "지원한 이력서 조회 성공";

        return new SuccessApiResponse<>(httpStatus, message, dto);
    }

    @ApiGetCoverLetterInApplicationLetter
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{applicationLetterId}/cover-letter")
    public CommonApiResponse getCoverLetter(@PathVariable Long applicationLetterId) {
        CoverLetterInApplicationLetterResponseDto dto = applicationLetterService.getCoverLetter(applicationLetterId);

        int httpStatus = HttpStatus.OK.value();
        String message = "지원한 자기소개서 조회 성공";

        return new SuccessApiResponse<>(httpStatus, message, dto);
    }
}