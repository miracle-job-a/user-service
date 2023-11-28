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
        summary = "이력서 수정",
        description = "유저의 이력서를 수정하는 API",
        responses = {
                @ApiResponse(
                        responseCode = SwaggerMsgUtil.ResponseCode.OK,
                        description = "이력서 수정 성공",
                        content = @Content(
                                mediaType = APPLICATION_JSON,
                                examples = @ExampleObject(
                                        name = "성공",
                                        value = """
                                                {
                                                  "httpStatus": 200,
                                                  "message": "이력서 수정 성공",
                                                  "data": true
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
                                                name = "이력서가 존재하지 않음",
                                                value = """
                                                {
                                                  "httpStatus": 400,
                                                  "message": "이력서가 존재하지 않습니다.",
                                                  "code": "400_1",
                                                  "exception": "NoSuchResumeException"
                                                }
                                                """
                                        )
                                },
                                schema = @Schema(implementation = ErrorApiResponse.class)
                        )
                )
        }
)
public @interface ApiPutResume {
}
