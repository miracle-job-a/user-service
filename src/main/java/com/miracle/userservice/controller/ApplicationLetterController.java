package com.miracle.userservice.controller;

import com.miracle.userservice.controller.response.CommonApiResponse;
import com.miracle.userservice.controller.response.SuccessApiResponse;
import com.miracle.userservice.controller.sort.ApplicationLetterListSort;
import com.miracle.userservice.dto.request.ApplicationLetterPostRequestDto;
import com.miracle.userservice.dto.request.validation.util.ValidationDefaultMsgUtil;
import com.miracle.userservice.dto.response.ApplicationLetterListResponseDto;
import com.miracle.userservice.dto.response.ApplicationLetterResponseDto;
import com.miracle.userservice.dto.response.CoverLetterInApplicationLetterResponseDto;
import com.miracle.userservice.dto.response.ResumeInApplicationLetterResponseDto;
import com.miracle.userservice.service.ApplicationLetterService;
import com.miracle.userservice.swagger.*;
import com.miracle.userservice.util.ParameterValidator;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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

    @ApiPostApplicationLetter
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public CommonApiResponse postApplicationLetter(@PathVariable Long userId, @Valid @RequestBody ApplicationLetterPostRequestDto dto) {
        boolean result = applicationLetterService.postApplicationLetter(userId, dto);

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

    @ApiDeleteApplicationLetter
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/{applicationLetterId}")
    public CommonApiResponse deleteApplicationLetter(@PathVariable Long applicationLetterId) {
        boolean result = applicationLetterService.deleteApplicationLetter(applicationLetterId);

        int httpStatus = HttpStatus.OK.value();
        String message = "지원서 삭제 성공";

        return new SuccessApiResponse<>(httpStatus, message, result);
    }

    @ApiGetApplicationLetterList
    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public CommonApiResponse getApplicationLetterList(
            @PathVariable Long userId,
            @Parameter(description = "Default Value = 1") @RequestParam(required = false, defaultValue = "1") int startPage,
            @Parameter(description = "Default Value = 5") @RequestParam(required = false, defaultValue = "5") int endPage,
            @Parameter(description = "Default Value = 10") @RequestParam(required = false, defaultValue = "10") int pageSize,
            @Parameter(name = "sort", description = "Value in ('SUBMIT_DATE_ASC', 'SUBMIT_DATE_DESC').\n Default Value = SUBMIT_DATE_ASC") @RequestParam(name = "sort", required = false, defaultValue = "SUBMIT_DATE_ASC") String sortStr
    ) {
        ParameterValidator.checkParameterWhenPaging(startPage, endPage, pageSize, ValidationDefaultMsgUtil.ApplicationLetterList.PAGING);
        ApplicationLetterListSort applicationLetterListSort = ParameterValidator.checkParameterEnum(ApplicationLetterListSort.class, sortStr, ValidationDefaultMsgUtil.ApplicationLetterList.SORT);
        Sort sort = applicationLetterListSort.toSort();

        startPage--;
        endPage--;
        List<List<ApplicationLetterListResponseDto>> result = new ArrayList<>();
        for (int i = startPage; i <= endPage; i++) {
            Pageable pageable = PageRequest.of(i, pageSize, sort);
            Page<ApplicationLetterListResponseDto> page = applicationLetterService.getApplicationLetterList(userId, pageable);
            result.add(page.getContent());
        }

        int httpStatus = HttpStatus.OK.value();
        String message = "지원서 목록 조회 성공";
        return new SuccessApiResponse<>(httpStatus, message, result);
    }
}
