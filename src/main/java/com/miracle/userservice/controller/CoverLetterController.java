package com.miracle.userservice.controller;

import com.miracle.userservice.controller.response.CommonApiResponse;
import com.miracle.userservice.controller.response.SuccessApiResponse;
import com.miracle.userservice.dto.request.CoverLetterPostRequestDto;
import com.miracle.userservice.dto.response.CoverLetterListResponseDto;
import com.miracle.userservice.dto.response.CoverLetterResponseDto;
import com.miracle.userservice.service.CoverLetterService;
import com.miracle.userservice.swagger.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
    public CommonApiResponse getCoverLetterList(@RequestParam Long userId) {
        List<CoverLetterListResponseDto> coverLetterList = coverLetterService.getCoverLetterList(userId);

        int httpStatus = HttpStatus.OK.value();
        String message = "자기소개서 목록 출력 성공";
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
