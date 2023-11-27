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
    @GetMapping("/{id}")
    public CommonApiResponse getCoverLetterDetail(@PathVariable Long id) {
        CoverLetterResponseDto dto = coverLetterService.getCoverLetterDetail(id);

        int httpStatus = HttpStatus.OK.value();
        String message = "자기소개서 조회 성공";
        return new SuccessApiResponse<>(httpStatus, message, dto);
    }

    @ApiPostCoverLetter
    @ResponseStatus(HttpStatus.OK)
    @PostMapping
    public CommonApiResponse postCoverLetter(@Valid @RequestBody CoverLetterPostRequestDto dto) {
        boolean result = coverLetterService.postCoverLetter(dto);

        int httpStatus = HttpStatus.OK.value();
        String message = "자기소개서 등록 성공";

        return new SuccessApiResponse<>(httpStatus, message, result);
    }

    @ApiPutCoverLetter
    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/{id}")
    public CommonApiResponse updateCoverLetter(@PathVariable Long id, @Valid @RequestBody CoverLetterPostRequestDto dto) {
        boolean result = coverLetterService.updateCoverLetter(id, dto);

        int httpStatus = HttpStatus.OK.value();
        String message = "자기소개서 수정 성공";
        return new SuccessApiResponse<>(httpStatus, message, result);
    }

    @ApiDeleteCoverLetter
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/{id}")
    public CommonApiResponse deleteCoverLetter(@PathVariable Long id) {
        boolean result = coverLetterService.deleteCoverLetter(id);

        int httpStatus = HttpStatus.OK.value();
        String message = "자기소개서 삭제 성공";
        return new SuccessApiResponse<>(httpStatus, message, result);
    }
}
