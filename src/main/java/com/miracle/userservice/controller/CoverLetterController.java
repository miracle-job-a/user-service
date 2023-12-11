package com.miracle.userservice.controller;

import com.miracle.userservice.controller.response.CommonApiResponse;
import com.miracle.userservice.controller.response.SuccessApiResponse;
import com.miracle.userservice.controller.sort.CoverLetterListSort;
import com.miracle.userservice.dto.request.CoverLetterPostRequestDto;
import com.miracle.userservice.dto.request.validation.util.ValidationDefaultMsgUtil;
import com.miracle.userservice.dto.response.CoverLetterListResponseDto;
import com.miracle.userservice.dto.response.CoverLetterResponseDto;
import com.miracle.userservice.service.CoverLetterService;
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
@RequestMapping("/v1/user/{userId}/cover-letter")
@RestController
public class CoverLetterController {

    private final CoverLetterService coverLetterService;

    @ApiGetCoverLetterList
    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public CommonApiResponse getCoverLetterList(
            @PathVariable Long userId,
            @Parameter(description = "Default Value = 1") @RequestParam(required = false, defaultValue = "1") int startPage,
            @Parameter(description = "Default Value = 10") @RequestParam(required = false, defaultValue = "10") int endPage,
            @Parameter(description = "Default Value = 5") @RequestParam(required = false, defaultValue = "5") int pageSize,
            @Parameter(name = "sort", description = "정렬 기준 IN ('MODIFIED_AT_ASC', 'MODIFIED_AT_DESC').\nDefalut Value = MODIFIED_AT_DESC") @RequestParam(required = false, defaultValue = "MODIFIED_AT_DESC", name = "sort") String sortStr
    ) {
        ParameterValidator.checkParameterWhenPaging(startPage, endPage, pageSize, ValidationDefaultMsgUtil.CoverLetterList.PAGING);
        CoverLetterListSort coverLetterListSort = ParameterValidator.checkParameterEnum(CoverLetterListSort.class, sortStr, ValidationDefaultMsgUtil.CoverLetterList.SORT);
        Sort sort = coverLetterListSort.toSort();

        startPage--;
        endPage--;
        List<List<CoverLetterListResponseDto>> coverLetterList = new ArrayList<>();
        for(int i = startPage; i <= endPage; i++) {
            Pageable pageable = PageRequest.of(i, pageSize, sort);
            Page<CoverLetterListResponseDto> page = coverLetterService.getCoverLetterList(userId, pageable);
            coverLetterList.add(page.getContent());
        }

        int httpStatus = HttpStatus.OK.value();
        String message = "자기소개서 목록 출력 성공";
        return new SuccessApiResponse<>(httpStatus, message, coverLetterList);
    }

    @ApiGetSearchCoverLetterList
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/search")
    public CommonApiResponse searchCoverLetter(
            @PathVariable Long userId,
            @Parameter(description = "검색 키워드") @RequestParam(required = false, defaultValue = "") String word,
            @Parameter(description = "Default Value = 1") @RequestParam(required = false, defaultValue = "1") int startPage,
            @Parameter(description = "Default Value = 10") @RequestParam(required = false, defaultValue = "10") int endPage,
            @Parameter(description = "Default Value = 5") @RequestParam(required = false, defaultValue = "5") int pageSize
    ) {
        ParameterValidator.checkParameterWhenPaging(startPage, endPage, pageSize, ValidationDefaultMsgUtil.CoverLetterList.PAGING);

        startPage--;
        endPage--;
        List<List<CoverLetterListResponseDto>> coverLetterList = new ArrayList<>();
        for(int i = startPage; i <= endPage; i++) {
            Pageable pageable = PageRequest.of(i, pageSize);
            Page<CoverLetterListResponseDto> page = coverLetterService.searchCoverLetter(userId, word, pageable);
            coverLetterList.add(page.getContent());
        }

        int httpStatus = HttpStatus.OK.value();
        String message = "자기소개서 검색 결과 출력 성공";
        return new SuccessApiResponse<>(httpStatus, message, coverLetterList);
    }

    @ApiGetCoverLetter
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{coverLetterId}")
    public CommonApiResponse getCoverLetterDetail(@PathVariable Long coverLetterId) {
        CoverLetterResponseDto dto = coverLetterService.getCoverLetterDetail(coverLetterId);

        int httpStatus = HttpStatus.OK.value();
        String message = "자기소개서 조회 성공";
        return new SuccessApiResponse<>(httpStatus, message, dto);
    }

    @ApiPostCoverLetter
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public CommonApiResponse postCoverLetter(@PathVariable Long userId, @Valid @RequestBody CoverLetterPostRequestDto dto) {
        boolean result = coverLetterService.postCoverLetter(userId, dto);

        int httpStatus = HttpStatus.CREATED.value();
        String message = "자기소개서 등록 성공";
        return new SuccessApiResponse<>(httpStatus, message, result);
    }

    @ApiPutCoverLetter
    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/{coverLetterId}")
    public CommonApiResponse updateCoverLetter(@PathVariable Long coverLetterId, @Valid @RequestBody CoverLetterPostRequestDto dto) {
        boolean result = coverLetterService.updateCoverLetter(coverLetterId, dto);

        int httpStatus = HttpStatus.OK.value();
        String message = "자기소개서 수정 성공";
        return new SuccessApiResponse<>(httpStatus, message, result);
    }

    @ApiDeleteCoverLetter
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/{coverLetterId}")
    public CommonApiResponse deleteCoverLetter(@PathVariable Long coverLetterId) {
        boolean result = coverLetterService.deleteCoverLetter(coverLetterId);

        int httpStatus = HttpStatus.OK.value();
        String message = "자기소개서 삭제 성공";
        return new SuccessApiResponse<>(httpStatus, message, result);
    }
}
