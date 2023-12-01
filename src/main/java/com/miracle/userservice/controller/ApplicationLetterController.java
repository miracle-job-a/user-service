package com.miracle.userservice.controller;

import com.miracle.userservice.controller.response.CommonApiResponse;
import com.miracle.userservice.controller.response.SuccessApiResponse;
import com.miracle.userservice.dto.response.ApplicationLetterListResponseDto;
import com.miracle.userservice.dto.response.ApplicationLetterResponseDto;
import com.miracle.userservice.dto.response.CoverLetterInApplicationLetterResponseDto;
import com.miracle.userservice.dto.response.ResumeInApplicationLetterResponseDto;
import com.miracle.userservice.service.ApplicationLetterService;
import com.miracle.userservice.swagger.*;
import com.miracle.userservice.util.ParameterValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

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

    @ApiGetResumeInApplicationLetter
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{applicationLetterId}/resume")
    public CommonApiResponse getResume(@PathVariable Long applicationLetterId, @PathVariable Long userId) {
        ResumeInApplicationLetterResponseDto dto = applicationLetterService.getResume(applicationLetterId, userId);

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

    @ApiGetApplicationLetterList
    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public CommonApiResponse getApplicationLetterList(
            @PathVariable Long userId,
            @RequestParam(required = false, defaultValue = "1") int startPage,
            @RequestParam(required = false, defaultValue = "5") int endPage,
            @RequestParam(required = false, defaultValue = "10") int pageSize
    ) {
        ParameterValidator.checkParameterWhenPaging(startPage, endPage, pageSize);

        startPage--;
        endPage--;
        List<List<ApplicationLetterListResponseDto>> result = new ArrayList<>();
        for (int i = startPage; i <= endPage; i++) {
            Pageable pageable = PageRequest.of(i, pageSize);
            Page<ApplicationLetterListResponseDto> page = applicationLetterService.getApplicationLetterList(userId, pageable);
            result.add(page.getContent());
        }

        int httpStatus = HttpStatus.OK.value();
        String message = "지원서 목록 조회 성공";
        return new SuccessApiResponse<>(httpStatus, message, result);
    }
}
