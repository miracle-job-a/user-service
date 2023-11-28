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
        summary = "유저 로그인 요청 처리",
        description = "유저 로그인 요청을 처리하는 API. DB의 유저 정보와 일치하면 성공",
        responses = {
                @ApiResponse(
                        responseCode = SwaggerMsgUtil.ResponseCode.OK,
                        description = "응답 성공",
                        content = @Content(
                                mediaType = SwaggerMsgUtil.MediaType.APPLICATION_JSON,
                                examples = {
                                        @ExampleObject(
                                                name = "로그인 성공",
                                                value = """
                                                        {
                                                          "httpStatus": 200,
                                                          "message": "로그인에 성공했습니다.",
                                                          "data": {
                                                            "success": true,
                                                            "id": 1,
                                                            "email": "useremail@email.com",
                                                            "name": "유저 이름"
                                                          }
                                                        }
                                                        """
                                        ),
                                        @ExampleObject(
                                                name = "로그인 실패",
                                                value = """
                                                        {
                                                          "httpStatus": 200,
                                                          "message": "로그인에 실패했습니다.",
                                                          "data": {
                                                            "success": false,
                                                            "id": null,
                                                            "email": null,
                                                            "name": null
                                                          }
                                                        }
                                                        """
                                        )
                                },
                                schema = @Schema(implementation = SuccessApiResponse.class)
                        )
                ),
                @ApiResponse(
                        responseCode = SwaggerMsgUtil.ResponseCode.BAD_REQUEST,
                        description = "데이터 검증 실패",
                        content = @Content(
                                mediaType = SwaggerMsgUtil.MediaType.APPLICATION_JSON,
                                examples = {
                                        @ExampleObject(
                                                name = "이메일 검증 실패",
                                                value = """
                                                        {
                                                          "httpStatus": 400,
                                                          "message": "이메일은 값이 입력되어야 합니다.",
                                                          "code": "400_1",
                                                          "exception": "MethodArgumentNotValidException"
                                                        }
                                                        """
                                        ),
                                        @ExampleObject(
                                                name = "비밀번호 검증 실패",
                                                value = """
                                                        {
                                                          "httpStatus": 400,
                                                          "message": "비밀번호는 값이 입력되어야 합니다.",
                                                          "code": "400_2",
                                                          "exception": "MethodArgumentNotValidException"
                                                        }
                                                        """
                                        )
                                },
                                schema = @Schema(implementation = ErrorApiResponse.class)
                        )
                )
        }
)
public @interface ApiPostUserLogin {
}
