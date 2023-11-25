package com.miracle.userservice.controller;

import com.miracle.userservice.controller.response.CommonApiResponse;
import com.miracle.userservice.controller.response.SuccessApiResponse;
import com.miracle.userservice.dto.response.CoverLetterListResponseDto;
import com.miracle.userservice.dto.response.CoverLetterResponseDto;
import com.miracle.userservice.service.CoverLetterService;
import com.miracle.userservice.swagger.ApiCoverLetterDelete;
import com.miracle.userservice.swagger.ApiCoverLetterRead;
import com.miracle.userservice.swagger.ApiGetCoverLetterList;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/v1/user/{id}/cover-letter")
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

    @ApiCoverLetterRead
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/detail/{id}")
    public CommonApiResponse getCoverLetterDetail(@PathVariable Long id) {
        CoverLetterResponseDto dto = coverLetterService.getCoverLetterDetail(id);

        int httpStatus = HttpStatus.OK.value();
        String message = "자기소개서 조회 성공";
        return new SuccessApiResponse<>(httpStatus, message, dto);
    }

    @ApiCoverLetterDelete
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/{id}")
    public CommonApiResponse deleteCoverLetter(@PathVariable Long id) {
        boolean result = coverLetterService.deleteCoverLetter(id);

        int httpStatus = HttpStatus.OK.value();
        String message = "자기소개서 삭제 성공";
        return new SuccessApiResponse<>(httpStatus, message, result);
    }
}
