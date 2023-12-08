package com.miracle.userservice.swagger;

import com.miracle.userservice.controller.response.ErrorApiResponse;
import com.miracle.userservice.controller.response.SuccessApiResponse;
import com.miracle.userservice.swagger.util.SwaggerMsgUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;

@Target(METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Operation(
        summary = "공고 지원자 목록 조회",
        description = "특정 공고의 지원자 목록을 조회하는 API",
        responses = {
                @ApiResponse(
                        responseCode = SwaggerMsgUtil.ResponseCode.OK,
                        description = "조회 성공",
                        content = @Content(
                                mediaType = SwaggerMsgUtil.MediaType.APPLICATION_JSON,
                                examples = @ExampleObject(
                                        name = "성공",
                                        value = """
                                                {
                                                    "httpStatus": 200,
                                                    "message": "지원자 목록 응답 성공",
                                                    "data": [
                                                        [
                                                            {
                                                                "applicationLetterId": 29,
                                                                "resumeTitle": "title1",
                                                                "email": email1@naver.com,
                                                                "name": "오스틴",
                                                                "address": "서울특별시 서초구 효령로 113",
                                                                "submitDate": "2023-11-30"
                                                            },
                                                            {
                                                                "applicationLetterId": 15,
                                                                "resumeTitle": "title2",
                                                                "email": email2@naver.com,
                                                                "name": "사장님",
                                                                "address": "서울특별시 서초구 효령로 114",
                                                                "submitDate": "2023-12-25"
                                                            }
                                                        ],
                                                        [
                                                            {
                                                                "applicationLetterId": 10,
                                                                "resumeTitle": "title3",
                                                                "email": email3@naver.com,
                                                                "name": "아이고",
                                                                "address": "서울특별시 서초구 효령로 153",
                                                                "submitDate": "2023-10-04"
                                                            },
                                                            {
                                                                "applicationLetterId": 50,
                                                                "resumeTitle": "title4",
                                                                "email": email4@naver.com,
                                                                "name": "월급",
                                                                "address": "서울특별시 서초구 효령로 119",
                                                                "submitDate": "2023-08-06"
                                                            }
                                                        ]
                                                    ]
                                                }
                                                """
                                ),
                                schema = @Schema(implementation = SuccessApiResponse.class)
                        )
                ),
                @ApiResponse(
                        responseCode = SwaggerMsgUtil.ResponseCode.BAD_REQUEST,
                        description = "잘못된 요청",
                        content = @Content(
                                mediaType = SwaggerMsgUtil.MediaType.APPLICATION_JSON,
                                examples = {
                                        @ExampleObject(
                                                name = "페이징 파라미터 형식 오류",
                                                value = """
                                                        {
                                                            "httpStatus": 400,
                                                            "message": "페이징 파라미터 형식이 올바르지 않습니다.",
                                                            "code": "400_1",
                                                            "exception": "InvalidParameterException"
                                                        }
                                                        """
                                        ),
                                        @ExampleObject(
                                                name = "정렬 파라미터 형식 오류",
                                                value = """
                                                        {
                                                            "httpStatus": 400,
                                                            "message": "정렬 파라미터 형식이 올바르지 않습니다. 값이 ('NAME', 'SUBMIT_DATE_ASC', 'SUBMIT_DATE_DESC') 중 하나여야 합니다.",
                                                            "code": "400_2",
                                                            "exception": "InvalidParameterException"
                                                        }
                                                        """
                                        )
                                },
                                schema = @Schema(implementation = ErrorApiResponse.class)
                        )
                )
        }
)
public @interface ApiGetApplicantList {
}
