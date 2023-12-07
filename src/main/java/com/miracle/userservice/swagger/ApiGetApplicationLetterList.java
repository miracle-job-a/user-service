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
        summary = "지원서 목록 조회",
        description = "특정 유저의 지원서 목록을 조회하는 API. 유저가 없을 경우 조회되지 않음",
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
                                                    "message": "지원서 목록 조회 성공",
                                                    "data": [
                                                        [
                                                            {
                                                                "applicationLetterId": 1,
                                                                "postId": "1",
                                                                "interviewId": "1",
                                                                "postType": "NORMAL",
                                                                "submitDate": "2023-11-30",
                                                                "applicationStatus": "PASS",
                                                                "job": "백엔드"
                                                            },
                                                            {
                                                                "applicationLetterId": 3,
                                                                "postId": "5",
                                                                "interviewId": "4",
                                                                "postType": "MZ",
                                                                "submitDate": "2023-11-29",
                                                                "applicationStatus": "IN_PROGRESS",
                                                                "job": "백엔드"
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
                                        )
                                },
                                schema = @Schema(implementation = ErrorApiResponse.class)
                        )
                )
        }
)
public @interface ApiGetApplicationLetterList {
}
