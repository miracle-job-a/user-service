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
        summary = "유저 목록 조회",
        description = "유저 목록 조회 요청을 처리하는 API. 파라미터로 원하는 페이지를 조회할 수 있음",
        responses = {
                @ApiResponse(
                        responseCode = SwaggerMsgUtil.ResponseCode.OK,
                        description = "유저 목록 조회 성공",
                        content = @Content(
                                mediaType = SwaggerMsgUtil.MediaType.APPLICATION_JSON,
                                examples = @ExampleObject(
                                        name = "성공",
                                        description = "회원 가입일을 기준으로 내림차순 정렬됨",
                                        value = """
                                                {
                                                     "httpStatus": 200,
                                                     "message": "회원 목록 조회 성공",
                                                     "data": [
                                                         [
                                                             {
                                                                 "id": 29,
                                                                 "email": "youremail28@naver.com",
                                                                 "name": "오스틴",
                                                                 "address": "서울특별시 서초구 효령로 113",
                                                                 "joinDate": "2023-11-30"
                                                             },
                                                             {
                                                                 "id": 28,
                                                                 "email": "youremail27@naver.com",
                                                                 "name": "오스틴",
                                                                 "address": "서울특별시 서초구 효령로 113",
                                                                 "joinDate": "2023-11-29"
                                                             }
                                                         ],
                                                         [
                                                             {
                                                                 "id": 26,
                                                                 "email": "youremail25@naver.com",
                                                                 "name": "오스틴",
                                                                 "address": "서울특별시 서초구 효령로 113",
                                                                 "joinDate": "2023-11-28"
                                                             },
                                                             {
                                                                 "id": 27,
                                                                 "email": "youremail26@naver.com",
                                                                 "name": "오스틴",
                                                                 "address": "서울특별시 서초구 효령로 113",
                                                                 "joinDate": "2023-11-25"
                                                             }
                                                         ],
                                                         [
                                                             {
                                                                 "id": 25,
                                                                 "email": "youremail24@naver.com",
                                                                 "name": "오스틴",
                                                                 "address": "서울특별시 서초구 효령로 113",
                                                                 "joinDate": "2023-11-18"
                                                             },
                                                             {
                                                                 "id": 24,
                                                                 "email": "youremail23@naver.com",
                                                                 "name": "오스틴",
                                                                 "address": "서울특별시 서초구 효령로 113",
                                                                 "joinDate": "2023-11-11"
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
                                                name = "파라미터 부호 검증 실패",
                                                value = """
                                                        {
                                                            "httpStatus": 400,
                                                            "message": "페이징 파라미터는 양수여야 합니다.",
                                                            "code": "400_1",
                                                            "exception": "InvalidParameterException"
                                                        }
                                                        """
                                        ),
                                        @ExampleObject(
                                                name = "파라미터 오류",
                                                value = """
                                                        {
                                                            "httpStatus": 400,
                                                            "message": "끝 페이지는 시작 페이지보다 커야합니다.",
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
public @interface ApiGetUserList {
}
