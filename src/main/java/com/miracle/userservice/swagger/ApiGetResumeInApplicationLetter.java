package com.miracle.userservice.swagger;

import com.miracle.userservice.controller.response.ErrorApiResponse;
import com.miracle.userservice.controller.response.SuccessApiResponse;
import com.miracle.userservice.swagger.util.SwaggerMsgUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static com.miracle.userservice.swagger.util.SwaggerMsgUtil.MediaType.APPLICATION_JSON;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Operation(
        summary = "지원한 이력서 조회",
        description = "유저의 지원서 중 이력서 내용을 조회하는 API",
        responses = {
                @ApiResponse(
                        responseCode = SwaggerMsgUtil.ResponseCode.OK,
                        description = "지원한 이력서 조회 성공",
                        content = @Content(
                                mediaType = APPLICATION_JSON,
                                examples = @ExampleObject(
                                        name = "성공",
                                        value = """
                                                {
                                                  "httpStatus": 200,
                                                  "message": "지원한 이력서 조회 성공",
                                                  "data": {
                                                    "resumeTitle": "software engineer",
                                                    "userName": "오스틴",
                                                    "userEmail": "miracle@gmail.com",
                                                    "userCareer": 0,
                                                    "userBirth": "2000-11-30",
                                                    "userPhone": "01012345678",
                                                    "userAddress": "서울시 서초구 효령로 335 플레이데이터",
                                                    "userJob": "backend engineer",
                                                    "userStackIdSet": [1, 2, 3],
                                                    "userEducation": "코딩대학교",
                                                    "userGitLink": "https://github.com/user",
                                                    "userCareerDetailList": ["Worked on project A", "Developed feature B"],
                                                    "userProjectList": ["Project 1", "Project 2", "Project 3"],
                                                    "userEtcList": ["Etc 1", "Etc 2", "Etc 3"]
                                                  }
                                                }
                                                """
                                ),
                                schema = @Schema(implementation = SuccessApiResponse.class)
                        )
                ),
                @ApiResponse(
                        responseCode = SwaggerMsgUtil.ResponseCode.BAD_REQUEST,
                        description = "지원서 조회 실패",
                        content = @Content(
                                mediaType = APPLICATION_JSON,
                                examples = @ExampleObject(
                                        name = "지원서가 존재하지 않음",
                                        value = """
                                                {
                                                  "httpStatus": 400,
                                                  "message": "지원서가 존재하지 않습니다.",
                                                  "code": "400_1",
                                                  "exception": "NoSuchApplicationLetterException"
                                                }
                                                """
                                ),
                                schema = @Schema(implementation = ErrorApiResponse.class)
                        )
                )
        }
)
public @interface ApiGetResumeInApplicationLetter {
}