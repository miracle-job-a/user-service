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
        summary = "이력서 조회",
        description = "이력서를 조회하는 API. 유저는 자신의 모든 이력서를 조회할 수 있으며, 기업은 해당 유저의 공개된 이력서만 조회할 수 있음",
        responses = {
                @ApiResponse(
                        responseCode = SwaggerMsgUtil.ResponseCode.OK,
                        description = "이력서 조회 성공",
                        content = @Content(
                                mediaType = APPLICATION_JSON,
                                examples = @ExampleObject(
                                        name = "성공",
                                        value = """
                                                {
                                                  "httpStatus": 200,
                                                  "message": "이력서 조회 성공",
                                                  "data": {
                                                    "id": 1,
                                                    "title": "Software Engineer",
                                                    "photo": "photo-url",
                                                    "career": 3,
                                                    "open": true,
                                                    "birth": "1990-01-01",
                                                    "phone": "010-1234-5678",
                                                    "education": "Bachelor's Degree",
                                                    "gitLink": "https://github.com/user",
                                                    "jobIdSet": [1, 2],
                                                    "stackIdSet": [3, 4],
                                                    "careerDetailList": ["Worked on project A", "Developed feature B"],
                                                    "projectList": ["Project X", "Project Y"],
                                                    "etcList": ["Skill A", "Skill B"]
                                                  }
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
public @interface ApiGetResume {
}
