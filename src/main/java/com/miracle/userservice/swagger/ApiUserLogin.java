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
        summary = "유저 로그인 요청 처리",
        description = "유저 로그인 요청을 처리하는 API. DB의 유저 정보와 일치하면 성공",
        responses = {
                @ApiResponse(
                        responseCode = SwaggerMsgUtil.ResponseCode.OK,
                        description = "로그인 성공",
                        content = @Content(
                                mediaType = SwaggerMsgUtil.MediaType.APPLICATION_JSON,
                                examples = @ExampleObject(
                                        name = "성공",
                                        value = """
                                                {
                                                    "httpStatus": 200,
                                                    "message": "로그인에 성공했습니다.",
                                                    "data": Boolean.TRUE
                                                }
                                                """
                                ),
                                schema = @Schema(implementation = SuccessApiResponse.class)
                        )

                ),
                @ApiResponse(
                        responseCode = SwaggerMsgUtil.ResponseCode.BAD_REQUEST,
                        description = "데이터 검증 실패",
                        content = @Content(
                                mediaType = SwaggerMsgUtil.MediaType.APPLICATION_JSON,
                                examples = @ExampleObject(
                                                name = "실패",
                                                value = """
                                                        {
                                                            "httpStatus": 400,
                                                            "message": "계정 정보가 일치하지 않습니다.",
                                                            "data": Boolean.FALSE
                                                        }
                                                        """
                                ),
                                schema = @Schema(implementation = SuccessApiResponse.class)
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
public @interface ApiUserLogin {
}
