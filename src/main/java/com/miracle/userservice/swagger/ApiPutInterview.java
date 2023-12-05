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
        summary = "면접 정보 수정",
        description = "유저의 면접 정보를 수정하는 API",
        responses = {
                @ApiResponse(
                        responseCode = SwaggerMsgUtil.ResponseCode.OK,
                        description = "면접 정보 수정 성공",
                        content = @Content(
                                mediaType = APPLICATION_JSON,
                                examples = @ExampleObject(
                                        name = "성공",
                                        value = """
                                                {
                                                  "httpStatus": 200,
                                                  "message": "면접 정보 수정 성공",
                                                  "data": true
                                                }
                                                """
                                ),
                                schema = @Schema(implementation = SuccessApiResponse.class)
                        )
                ),
                @ApiResponse(
                        responseCode = SwaggerMsgUtil.ResponseCode.BAD_REQUEST,
                        description = "면접 정보 수정 실패",
                        content = @Content(
                                mediaType = APPLICATION_JSON,
                                examples = {
                                        @ExampleObject(
                                                name = "유저 ID 검증 실패",
                                                value = """
                                                        {
                                                          "httpStatus": 400,
                                                          "message": "유저 아이디 값이 없거나 양수가 아닙니다.",
                                                          "code": "400_1",
                                                          "exception": "MethodArgumentNotValidException"
                                                        }
                                                        """
                                        ),
                                        @ExampleObject(
                                                name = "지원서 ID 검증 실패",
                                                value = """
                                                        {
                                                          "httpStatus": 400,
                                                          "message": "지원서 아이디 값이 없거나 양수가 아닙니다.",
                                                          "code": "400_2",
                                                          "exception": "MethodArgumentNotValidException"
                                                        }
                                                        """
                                        ),
                                        @ExampleObject(
                                                name = "QNA 검증 실패",
                                                value = """
                                                        {
                                                          "httpStatus": 400,
                                                          "message": "Collection이 null입니다.",
                                                          "code": "400_3",
                                                          "exception": "MethodArgumentNotValidException"
                                                        }
                                                        """
                                        ),
                                        @ExampleObject(
                                                name = "지원서가 존재하지 않음",
                                                value = """
                                                        {
                                                          "httpStatus": 400,
                                                          "message": "지원서가 존재하지 않습니다.",
                                                          "code": "400_4",
                                                          "exception": "NoSuchApplicationLetterException"
                                                        }
                                                        """
                                        ),
                                        @ExampleObject(
                                                name = "면접 정보가 존재하지 않음",
                                                value = """
                                                        {
                                                          "httpStatus": 400,
                                                          "message": "면접 정보가 존재하지 않습니다.",
                                                          "code": "400_5",
                                                          "exception": "NoSuchInterviewException"
                                                        }
                                                        """
                                        )
                                },
                                schema = @Schema(implementation = ErrorApiResponse.class)
                        )
                ),
                @ApiResponse(
                responseCode = SwaggerMsgUtil.ResponseCode.NOT_ACCEPTABLE,
                        description = "면접 정보 등록 실패",
                        content = @Content(
                                mediaType = APPLICATION_JSON,
                                examples = @ExampleObject(
                                        name = "면접 정보 최대 저장 개수 초과",
                                        value = """
                                                {
                                                  "httpStatus": 406,
                                                  "message": "면접 정보는 최대 5개까지 저장 가능합니다.",
                                                  "code": "406",
                                                  "exception": "OverflowException"
                                                }
                                                """
                                ),
                                schema = @Schema(implementation = ErrorApiResponse.class)
                        )
                )

        }
)
public @interface ApiPutInterview {
}