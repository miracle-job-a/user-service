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
        summary = "자기소개서 조회",
        description = "유저의 자기소개서 상세 내용을 조회하는 API",
        responses = {
                @ApiResponse(
                        responseCode = SwaggerMsgUtil.ResponseCode.OK,
                        description = "자기소개서 조회 성공",
                        content = @Content(
                                mediaType = APPLICATION_JSON,
                                examples = @ExampleObject(
                                        name = "성공",
                                        value = """
                                                {
                                                  "httpStatus": 200,
                                                  "message": "자기소개서 조회 성공",
                                                  "data": {
                                                    "id": 1,
                                                    "title": "한 달 만에 채용사이트를 구현할 수 있었던 비결",
                                                    "modifiedAt": "2023-11-23",
                                                    "qnaList": [
                                                        {
                                                            "question": "서로 의견이 상충할 때 해결했던 경험",
                                                            "answer": "가장 먼저 경청이 중요하다고 생각하여 서로의 의견을 모두 들어본 후 타당한 근거를 제시하여 설득"
                                                        },
                                                        {
                                                            "question": "가장 열정적으로 임했던 경험",
                                                            "answer": "하루 12시간 밥먹고 코딩만 해서 한 달만에 채용 사이트를 구축하였음"
                                                        }
                                                    ],
                                                  }
                                                }
                                                """
                                ),
                                schema = @Schema(implementation = SuccessApiResponse.class)
                        )
                ),
                @ApiResponse(
                        responseCode = SwaggerMsgUtil.ResponseCode.BAD_REQUEST,
                        description = "자기소개서 조회 실패",
                        content = @Content(
                                mediaType = APPLICATION_JSON,
                                examples = @ExampleObject(
                                        name = "자기소개서가 존재하지 않음",
                                        value = """
                                                {
                                                  "httpStatus": 400,
                                                  "message": "자기소개서가 존재하지 않습니다.",
                                                  "code": "400",
                                                  "exception": "NoSuchCoverLetterException"
                                                }
                                                """
                                ),
                                schema = @Schema(implementation = ErrorApiResponse.class)
                        )
                ),
                @ApiResponse(
                        responseCode = SwaggerMsgUtil.ResponseCode.UNAUTHORIZED,
                        description = "비정상적인 요청",
                        content = @Content(
                                mediaType = APPLICATION_JSON,
                                examples = @ExampleObject(
                                        name = "토큰 검증 실패",
                                        value = """
                                                {
                                                  "httpStatus": 401,
                                                  "message": "올바르지 않은 요청입니다.",
                                                  "code": "401",
                                                  "exception": "InvalidRequestStateException"
                                                }
                                                """
                                ),
                                schema = @Schema(implementation = ErrorApiResponse.class)
                        )
                )
        }
)
public @interface ApiGetCoverLetter {
}
