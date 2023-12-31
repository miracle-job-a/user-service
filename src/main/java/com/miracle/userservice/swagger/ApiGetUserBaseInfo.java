package com.miracle.userservice.swagger;

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
        summary = "유저 기본 정보 요청 처리",
        description = "유저의 기본 정보 요청을 처리하는 API. 해당 유저가 존재하지 않으면 조회되지 않음",
        responses = {
                @ApiResponse(
                        responseCode = SwaggerMsgUtil.ResponseCode.OK,
                        description = "유저 기본 정보 조회 성공",
                        content = @Content(
                                mediaType = SwaggerMsgUtil.MediaType.APPLICATION_JSON,
                                examples = @ExampleObject(
                                        name = "성공",
                                        value = """
                                                {
                                                  "httpStatus": 200,
                                                  "message": "유저 기본 정보 조회 성공",
                                                  "data": {
                                                    "email": "youremail@naver.com",
                                                    "name": "오스틴",
                                                    "phone": "01012345678",
                                                    "birth": "2017-01-01",
                                                    "address": "서울특별시 서초구 효령로 113",
                                                    "stackIdSet": [
                                                      3,
                                                      1,
                                                      5
                                                    ]
                                                  }
                                                }
                                                """
                                ),
                                schema = @Schema(implementation = SuccessApiResponse.class)
                        )
                )
        }
)
public @interface ApiGetUserBaseInfo {
}
