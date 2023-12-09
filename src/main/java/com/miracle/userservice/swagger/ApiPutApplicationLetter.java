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
        summary = "지원서 수정",
        description = "유저의 지원서 상태 정보를 수정하는 API",
        responses = {
                @ApiResponse(
                        responseCode = SwaggerMsgUtil.ResponseCode.OK,
                        description = "지원서 상태 정보 수정 성공",
                        content = @Content(
                                mediaType = APPLICATION_JSON,
                                examples = @ExampleObject(
                                        name = "성공",
                                        value = """
                                                {
                                                  "httpStatus": 200,
                                                  "message": "지원서 수정 성공",
                                                  "data": true
                                                }
                                                """
                                ),
                                schema = @Schema(implementation = SuccessApiResponse.class)
                        )
                ),
                @ApiResponse(
                        responseCode = SwaggerMsgUtil.ResponseCode.BAD_REQUEST,
                        description = "지원서 수정 실패",
                        content = @Content(
                                mediaType = APPLICATION_JSON,
                                examples = {
                                        @ExampleObject(
                                                name = "지원 상태 파라미터 값 오류",
                                                value = """
                                                        {
                                                            "httpStatus": 400,
                                                            "message": "지원 상태 값이 올바르지 않습니다. 값이 ('PASS', 'FAIL', 'IN_PROGRESS') 중 하나여야 합니다.",
                                                            "code": "400_1",
                                                            "exception": "InvalidParameterException"
                                                        }
                                                        """
                                        ),
                                        @ExampleObject(
                                                name = "지원서가 존재하지 않음",
                                                value = """
                                                        {
                                                          "httpStatus": 400,
                                                          "message": "지원서가 존재하지 않습니다.",
                                                          "code": "400_2",
                                                          "exception": "NoSuchApplicationLetterException"
                                                        }
                                                        """
                                        )
                                },
                                schema = @Schema(implementation = ErrorApiResponse.class)
                        )
                )

        }
)
public @interface ApiPutApplicationLetter {
}