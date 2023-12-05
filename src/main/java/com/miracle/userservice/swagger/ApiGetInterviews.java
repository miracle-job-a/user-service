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

import static com.miracle.userservice.swagger.util.SwaggerMsgUtil.MediaType.APPLICATION_JSON;
import static java.lang.annotation.ElementType.METHOD;

@Target(METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Operation(
        summary = "해당 지원서에 대한 면접 정보 조회 요청 처리",
        description = "면접 정보 조회 요청을 처리하는 API. 해당 지원서에 대한 면접 ID가 null이면 null 반환",
        responses = {
                @ApiResponse(
                        responseCode = SwaggerMsgUtil.ResponseCode.OK,
                        description = "면접 정보 조회 성공",
                        content = @Content(
                                mediaType = SwaggerMsgUtil.MediaType.APPLICATION_JSON,
                                examples = @ExampleObject(
                                        name = "성공",
                                        value = """
                                                {
                                                  "httpStatus": 200,
                                                  "message": "면접 정보 조회 성공",
                                                  "data": {
                                                    "qnaList": [
                                                        {
                                                            "question": "서로 의견이 상충할 때 해결했던 경험",
                                                            "answer": "가장 먼저 경청이 중요하다고 생각하여 서로의 의견을 모두 들어본 후 타당한 근거를 제시하여 설득"
                                                        },
                                                        {
                                                            "question": "가장 열정적으로 임했던 경험",
                                                            "answer": "하루 12시간씩 코딩에 집중하여 한 달만에 채용 사이트를 구축"
                                                        }
                                                    ]
                                                  }
                                                }
                                                """
                                ),
                                schema = @Schema(implementation = SuccessApiResponse.class)
                        )
                ),
                @ApiResponse(
                        responseCode = SwaggerMsgUtil.ResponseCode.BAD_REQUEST,
                        description = "면접 정보 조회 실패",
                        content = @Content(
                                mediaType = APPLICATION_JSON,
                                examples = @ExampleObject(
                                        name = "면접 정보가 존재하지 않음",
                                        value = """
                                                {
                                                  "httpStatus": 400,
                                                  "message": "면접 정보가 존재하지 않습니다.",
                                                  "code": "400_1",
                                                  "exception": "NoSuchInterviewException"
                                                }
                                                """
                                ),
                                schema = @Schema(implementation = ErrorApiResponse.class)
                        )
                )
        }
)
public @interface ApiGetInterviews {
}
