package com.miracle.userservice.controller;

import com.miracle.userservice.controller.response.CommonApiResponse;
import com.miracle.userservice.controller.response.SuccessApiResponse;
import com.miracle.userservice.dto.response.ApplicantListResponseDto;
import com.miracle.userservice.service.ApplicationLetterService;
import com.miracle.userservice.swagger.ApiGetApplicantNumber;
import com.miracle.userservice.swagger.DefaultPathDocket;
import com.miracle.userservice.util.ParameterValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@DefaultPathDocket
@RequiredArgsConstructor
@RequestMapping("/v1/post/{postId}")
@RestController
public class PostController {

    private final ApplicationLetterService applicationLetterService;

    @ApiGetApplicantNumber
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/applicant/num")
    public CommonApiResponse getNumberOfApplicant(@PathVariable Long postId) {
        long count = applicationLetterService.getNumberOfApplicant(postId);
        int httpStatus = HttpStatus.OK.value();
        String message = "지원자 수 응답 성공";
        return new SuccessApiResponse<>(httpStatus, message, count);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/applicant/list")
    public CommonApiResponse getListOfApplicant(
            @PathVariable Long postId,
            @RequestParam(required = false, defaultValue = "1") int startPage,
            @RequestParam(required = false, defaultValue = "5") int endPage,
            @RequestParam(required = false, defaultValue = "10") int pageSize
    ) {
        ParameterValidator.checkParameterWhenPaging(startPage, endPage, pageSize);

        startPage--;
        endPage--;
        List<List<ApplicantListResponseDto>> result = new ArrayList<>();
        for (int i = startPage; i <= endPage; i++) {
            Pageable pageable = PageRequest.of(i, pageSize);
            Page<ApplicantListResponseDto> page = applicationLetterService.getApplicantList(postId, pageable);
            result.add(page.getContent());
        }

        int httpStatus = HttpStatus.OK.value();
        String message = "지원자 목록 응답 성공";
        return new SuccessApiResponse<>(httpStatus, message, result);
    }
}
