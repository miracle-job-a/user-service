package com.miracle.userservice.swagger;

import com.miracle.userservice.dto.response.ErrorApiResponse;
import com.miracle.userservice.dto.response.SuccessApiResponse;
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
        summary = "이메일 중복 체크",
        description = "이메일 중복 체크 요청을 처리하는 API. 중복인 경우 true, 그렇지 않은 경우 false를 반환함",
        responses = {
                @ApiResponse(
                        responseCode = SwaggerMsgUtil.ResponseCode.OK,
                        description = "응답 성공",
                        content = @Content(
                                mediaType = SwaggerMsgUtil.MediaType.APPLICATION_JSON,
                                examples = {
                                        @ExampleObject(
                                                name = "이메일 중복 X",
                                                value = """
                                                        {
                                                          "httpStatus": 200,
                                                          "message": "사용 가능한 이메일입니다.",
                                                          "data": false
                                                        }
                                                        """
                                        ),
                                        @ExampleObject(
                                                name = "이메일 중복 O",
                                                value = """
                                                        {
                                                          "httpStatus": 200,
                                                          "message": "사용할 수 없는 이메일입니다.",
                                                          "data": true
                                                        }
                                                        """
                                        )
                                },
                                schema = @Schema(implementation = SuccessApiResponse.class)
                        )
                ),
                @ApiResponse(
                        responseCode = SwaggerMsgUtil.ResponseCode.BAD_REQUEST,
                        description = "이메일 검증",
                        content = @Content(
                                mediaType = SwaggerMsgUtil.MediaType.APPLICATION_JSON,
                                examples = @ExampleObject(
                                        name = "이메일 검증 실패",
                                        value = """
                                                {
                                                  "httpStatus": 400,
                                                  "message": "이메일 형식이 올바르지 않습니다.",
                                                  "code": "400",
                                                  "exception": "InvalidEmailException"
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
                                mediaType = SwaggerMsgUtil.MediaType.APPLICATION_JSON,
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

public @interface ApiCheckEmail {
}
