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
        summary = "지원서 등록",
        description = "유저의 지원서를 등록하는 API",
        responses = {
                @ApiResponse(
                        responseCode = SwaggerMsgUtil.ResponseCode.CREATED,
                        description = "지원서 등록 성공",
                        content = @Content(
                                mediaType = APPLICATION_JSON,
                                examples = @ExampleObject(
                                        name = "성공",
                                        value = """
                                                {
                                                  "httpStatus": 201,
                                                  "message": "해당 공고에 지원이 완료되었습니다.",
                                                  "data": true
                                                }
                                                """
                                ),
                                schema = @Schema(implementation = SuccessApiResponse.class)
                        )
                ),
                @ApiResponse(
                        responseCode = SwaggerMsgUtil.ResponseCode.BAD_REQUEST,
                        description = "면접 정보 등록 실패",
                        content = @Content(
                                mediaType = APPLICATION_JSON,
                                examples = {
                                        @ExampleObject(
                                                name = "이력서 ID 검증 실패",
                                                value = """
                                                        {
                                                          "httpStatus": 400,
                                                          "message": "이력서 아이디 값이 없거나 양수가 아닙니다.",
                                                          "code": "400_1",
                                                          "exception": "MethodArgumentNotValidException"
                                                        }
                                                        """
                                        ),
                                        @ExampleObject(
                                                name = "자기소개서 ID 검증 실패",
                                                value = """
                                                        {
                                                          "httpStatus": 400,
                                                          "message": "자기소개서 아이디 값이 없거나 양수가 아닙니다.",
                                                          "code": "400_2",
                                                          "exception": "MethodArgumentNotValidException"
                                                        }
                                                        """
                                        ),
                                        @ExampleObject(
                                                name = "공고 타입 검증 실패",
                                                value = """
                                                        {
                                                          "httpStatus": 400,
                                                          "message": "공고 타입이 없거나 형식이 올바르지 않습니다.",
                                                          "code": "400_3",
                                                          "exception": "MethodArgumentNotValidException"
                                                        }
                                                        """
                                        ),
                                        @ExampleObject(
                                                name = "공고 ID 검증 실패",
                                                value = """
                                                        {
                                                          "httpStatus": 400,
                                                          "message": "공고 아이디 값이 없거나 양수가 아닙니다.",
                                                          "code": "400_4",
                                                          "exception": "MethodArgumentNotValidException"
                                                        }
                                                        """
                                        ),
                                        @ExampleObject(
                                                name = "지원 일자 검증 실패",
                                                value = """
                                                        {
                                                          "httpStatus": 400,
                                                          "message": "지원 일자 값이 없습니다.",
                                                          "code": "400_5",
                                                          "exception": "MethodArgumentNotValidException"
                                                        }
                                                        """
                                        ),
                                        @ExampleObject(
                                                name = "지원 상태 검증 실패",
                                                value = """
                                                        {
                                                          "httpStatus": 400,
                                                          "message": "지원 상태 값이 없거나 형식이 올바르지 않습니다.",
                                                          "code": "400_6",
                                                          "exception": "MethodArgumentNotValidException"
                                                        }
                                                        """
                                        ),
                                        @ExampleObject(
                                                name = "유저 직무 검증 실패",
                                                value = """
                                                        {
                                                          "httpStatus": 400,
                                                          "message": "유저 직무가 글자 제한 수를 초과했습니다.",
                                                          "code": "400_7",
                                                          "exception": "MethodArgumentNotValidException"
                                                        }
                                                        """
                                        ),
                                        @ExampleObject(
                                                name = "이력서가 존재하지 않음",
                                                value = """
                                                        {
                                                          "httpStatus": 400,
                                                          "message": "이력서가 존재하지 않습니다.",
                                                          "code": "400_8",
                                                          "exception": "NoSuchResumeException"
                                                        }
                                                        """
                                        ),
                                        @ExampleObject(
                                                name = "자기소개서가 존재하지 않음",
                                                value = """
                                                        {
                                                          "httpStatus": 400,
                                                          "message": "자기소개서가 존재하지 않습니다.",
                                                          "code": "400_9",
                                                          "exception": "NoSuchCoverLetterException"
                                                        }
                                                        """
                                        ),
                                        @ExampleObject(
                                                name = "지원서가 이미 존재함",
                                                value = """
                                                        {
                                                          "httpStatus": 400,
                                                          "message": "이미 지원한 공고입니다.",
                                                          "code": "400_10",
                                                          "exception": "DuplicateApplicationLetterException"
                                                        }
                                                        """
                                        )
                                },
                                schema = @Schema(implementation = ErrorApiResponse.class)
                        )
                )
        }
)
public @interface ApiPostApplicationLetter {
}
