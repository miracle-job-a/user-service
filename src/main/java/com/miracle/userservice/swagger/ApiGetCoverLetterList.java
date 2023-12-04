package com.miracle.userservice.swagger;

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
        summary = "자기소개서 목록 출력 요청 처리",
        description = "자기소개서 목록 출력 요청을 처리하는 API. 성공 시 유저의 자기소개서 목록이 출력됨",
        responses = {
                @ApiResponse(
                        responseCode = SwaggerMsgUtil.ResponseCode.OK,
                        description = "자기소개서 목록 출력 성공",
                        content = @Content(
                                mediaType = SwaggerMsgUtil.MediaType.APPLICATION_JSON,
                                examples = @ExampleObject(
                                        name = "성공",
                                        value = """
                                                {
                                                  "httpStatus": 200,
                                                  "message": "자기소개서 목록 출력 성공",
                                                  "data": [
                                                              {
                                                                  "id": 18,
                                                                  "title": "비전공자의 커리어 전환기 : 야너두 할수있어",
                                                                  "modifiedAt": "2023-12-04"
                                                              },
                                                              {
                                                                  "id": 17,
                                                                  "title": "성실함 : Blog 1일 1업로드 실천",
                                                                  "modifiedAt": "2023-12-04"
                                                              },
                                                              {
                                                                  "id": 16,
                                                                  "title": "한 달 만에 채용사이트를 구현할 수 있었던 비결",
                                                                  "modifiedAt": "2023-12-04"
                                                              },
                                                              {
                                                                  "id": 15,
                                                                  "title": "비전공자의 커리어 전환기 : 야너두 할수있어",
                                                                  "modifiedAt": "2023-12-04"
                                                              },
                                                              {
                                                                  "id": 14,
                                                                  "title": "성실함 : Blog 1일 1업로드 실천",
                                                                  "modifiedAt": "2023-12-04"
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
public @interface ApiGetCoverLetterList {
}