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
        summary = "유저 회원 가입 요청 처리",
        description = "유저의 회원 가입 요청을 처리하는 API. 성공 시 DB에 유저의 정보가 저장됨",
        responses = {
                @ApiResponse(
                        responseCode = SwaggerMsgUtil.ResponseCode.OK,
                        description = "회원 가입 성공",
                        content = @Content(
                                mediaType = SwaggerMsgUtil.MediaType.APPLICATION_JSON,
                                examples = @ExampleObject(
                                        name = "성공",
                                        value = """
                                                {
                                                  "httpStatus": 200,
                                                  "message": "회원 가입 성공",
                                                  "data": null
                                                }
                                                """
                                ),
                                schema = @Schema(implementation = SuccessApiResponse.class)
                        )
                ),
                @ApiResponse(
                        responseCode = SwaggerMsgUtil.ResponseCode.BAD_REQUEST_1,
                        description = "데이터 검증 실패",
                        content = @Content(
                                mediaType = SwaggerMsgUtil.MediaType.APPLICATION_JSON,
                                examples = {
                                        @ExampleObject(
                                                name = "이메일 검증 실패",
                                                value = """
                                                        {
                                                          "httpStatus": 400,
                                                          "message": "이메일 형식이 올바르지 않습니다.",
                                                          "code": "400_1",
                                                          "exception": "MethodArgumentNotValidException"
                                                        }
                                                        """
                                        ),
                                        @ExampleObject(
                                                name = "이름 검증 실패",
                                                value = """
                                                        {
                                                          "httpStatus": 400,
                                                          "message": "이름 형식이 올바르지 않습니다.",
                                                          "code": "400_2",
                                                          "exception": "MethodArgumentNotValidException"
                                                        }
                                                        """
                                        ),
                                        @ExampleObject(
                                                name = "비밀번호 검증 실패",
                                                value = """
                                                        {
                                                          "httpStatus": 400,
                                                          "message": "비밀번호 형식이 올바르지 않습니다.",
                                                          "code": "400_3",
                                                          "exception": "MethodArgumentNotValidException"
                                                        }
                                                        """
                                        ),
                                        @ExampleObject(
                                                name = "전화번호 검증 실패",
                                                value = """
                                                        {
                                                          "httpStatus": 400,
                                                          "message": "전화번호 형식이 올바르지 않습니다.",
                                                          "code": "400_4",
                                                          "exception": "MethodArgumentNotValidException"
                                                        }
                                                        """
                                        ),
                                        @ExampleObject(
                                                name = "생년월일 검증 실패",
                                                value = """
                                                        {
                                                          "httpStatus": 400,
                                                          "message": "생년월일 형식이 올바르지 않습니다.",
                                                          "code": "400_5",
                                                          "exception": "MethodArgumentNotValidException"
                                                        }
                                                        """
                                        ),
                                        @ExampleObject(
                                                name = "주소 검증 실패",
                                                value = """
                                                        {
                                                          "httpStatus": 400,
                                                          "message": "주소 형식이 올바르지 않습니다.",
                                                          "code": "400_6",
                                                          "exception": "MethodArgumentNotValidException"
                                                        }
                                                        """
                                        )
                                },
                                schema = @Schema(implementation = ErrorApiResponse.class)
                        )
                ),
                @ApiResponse(
                        responseCode = SwaggerMsgUtil.ResponseCode.BAD_REQUEST_2,
                        description = "회원 가입 실패",
                        content = @Content(
                                mediaType = SwaggerMsgUtil.MediaType.APPLICATION_JSON,
                                examples = @ExampleObject(
                                        name = "이메일 중복",
                                        value = """
                                                {
                                                  "httpStatus": 400,
                                                  "message": "이메일 중복입니다.",
                                                  "code": "400",
                                                  "exception": DuplicateEmailException"
                                                }
                                                """
                                ),
                                schema = @Schema(implementation = ErrorApiResponse.class)
                        )
                )
        }
)
public @interface ApiPostUserJoin {
}
