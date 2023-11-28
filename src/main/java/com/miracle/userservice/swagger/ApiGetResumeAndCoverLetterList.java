package com.miracle.userservice.swagger;

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
        summary = "이력서 및 자기소개서 목록 조회",
        description = "이력서 및 자기소개서 목록을 반환하는 API",
        responses = {
                @ApiResponse(
                        responseCode = SwaggerMsgUtil.ResponseCode.OK,
                        description = "이력서 및 자기소개서 목록 조회 성공",
                        content = @Content(
                                mediaType = APPLICATION_JSON,
                                examples = @ExampleObject(
                                        name = "성공",
                                        value = """
                                                {
                                                  "httpStatus": 200,
                                                  "message": "이력서 및 자기소개서 목록 조회 성공",
                                                  "data": {
                                                            "resumeList": [
                                                                {
                                                                    "id": 1,
                                                                    "title": "Software Engineer"
                                                                },
                                                                {
                                                                    "id": 2,
                                                                    "title": "Data Scientist"
                                                                }
                                                            ],
                                                            "coverLetterList": [
                                                                {
                                                                    "id": 1,
                                                                    "title": "한 달 만에 채용사이트를 구현할 수 있었던 비결"
                                                                },
                                                                {
                                                                    "id": 2,
                                                                    "title": "성실함 : Blog 1일 1업로드 실천"
                                                                }
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
public @interface ApiGetResumeAndCoverLetterList {
}