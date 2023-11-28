package com.miracle.userservice.swagger;

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
        summary = "특정 유저의 이력서 목록 조회",
        description = "특정 유저가 보유하고 있는 이력서 목록을 반환하는 API",
        responses = {
                @ApiResponse(
                        responseCode = SwaggerMsgUtil.ResponseCode.OK,
                        description = "이력서 목록 조회 성공",
                        content = @Content(
                                mediaType = APPLICATION_JSON,
                                examples = @ExampleObject(
                                        name = "성공",
                                        value = """
                                                {
                                                  "httpStatus": 200,
                                                  "message": "이력서 목록 조회 성공",
                                                  "data": [
                                                    {
                                                      "id": 1,
                                                      "title": "Software Engineer",
                                                      "jobIdSet": [1, 2],
                                                      "modifiedAt": "2023-10-31-12-30-45",
                                                      "open": true
                                                    },
                                                    {
                                                      "id": 2,
                                                      "title": "Data Scientist",
                                                      "jobIdSet": [3],
                                                      "modifiedAt": "2023-10-31-13-45-00",
                                                      "open": false
                                                    }
                                                  ]
                                                }
                                                """
                                ),
                                schema = @Schema(implementation = SuccessApiResponse.class)
                        )
                )
        }
)
public @interface ApiGetResumes {
}
