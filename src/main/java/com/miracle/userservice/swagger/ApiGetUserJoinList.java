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
        summary = "회원 가입 목록 조회",
        description = "회원 가입 목록 조회 요청을 처리하는 API. 파라미터로 원하는 페이지를 조회할 수 있음",
        responses = {
                @ApiResponse(
                        responseCode = SwaggerMsgUtil.ResponseCode.OK,
                        description = "회원 가입 목록 조회 성공",
                        content = @Content(
                                mediaType = SwaggerMsgUtil.MediaType.APPLICATION_JSON,
                                examples = @ExampleObject(
                                        name = "성공",
                                        description = "회원 가입일을 기준으로 내림차순 정렬됨",
                                        value = """
                                                {
                                                    "httpStatus": 200,
                                                    "message": "회원 가입 목록 조회 성공",
                                                    "data": [
                                                        {
                                                            "userId": 31,
                                                            "email": "youremail30@naver.com",
                                                            "name": "오스틴",
                                                            "createdAt": [
                                                                2023,
                                                                11,
                                                                30,
                                                                15,
                                                                21,
                                                                43
                                                            ]
                                                        },
                                                        {
                                                            "userId": 30,
                                                            "email": "youremail29@naver.com",
                                                            "name": "오스틴",
                                                            "createdAt": [
                                                                2023,
                                                                11,
                                                                29,
                                                                15,
                                                                21,
                                                                42
                                                            ]
                                                        }
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
                                        )
                                },
                                schema = @Schema(implementation = ErrorApiResponse.class)
                        )
                )
        }
)
public @interface ApiGetUserJoinList {
}
