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
        summary = "자기소개서 검색 결과 출력 요청 처리",
        description = "자기소개서 검색 결과 출력 요청을 처리하는 API",
        responses = {
                @ApiResponse(
                        responseCode = SwaggerMsgUtil.ResponseCode.OK,
                        description = "자기소개서 검색 결과 출력 성공",
                        content = @Content(
                                mediaType = SwaggerMsgUtil.MediaType.APPLICATION_JSON,
                                examples = @ExampleObject(
                                        name = "성공",
                                        value = """
                                                {
                                                  "httpStatus": 200,
                                                  "message": "자기소개서 검색 결과 출력 성공",
                                                  "data": [
                                                              {
                                                                  "id": 19,
                                                                  "title": "비전공자의 커리어 전환기 : 야너두 할수있어",
                                                                  "modifiedAt": "2023-12-06"
                                                              },
                                                              {
                                                                  "id": 15,
                                                                  "title": "비전공자의 커리어 전환기 : 야너두 할수있어",
                                                                  "modifiedAt": "2023-12-05"
                                                              },
                                                              {
                                                                  "id": 9,
                                                                  "title": "비전공자의 커리어 전환기 : 야너두 할수있어",
                                                                  "modifiedAt": "2023-12-04"
                                                              },
                                                              {
                                                                  "id": 6,
                                                                  "title": "비전공자의 커리어 전환기 : 야너두 할수있어",
                                                                  "modifiedAt": "2023-12-02"
                                                              },
                                                              {
                                                                  "id": 3,
                                                                  "title": "비전공자의 커리어 전환기 : 야너두 할수있어",
                                                                  "modifiedAt": "2023-12-01"
                                                              }
                                                          ]
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
                                                name = "페이징 파라미터 형식 오류",
                                                value = """
                                                        {
                                                            "httpStatus": 400,
                                                            "message": "페이징 파라미터 형식이 올바르지 않습니다.",
                                                            "code": "400_1",
                                                            "exception": "InvalidParameterException"
                                                        }
                                                        """
                                        )
                                },
                                schema = @Schema(implementation = ErrorApiResponse.class)
                        )
                )
        }
)
public @interface ApiGetSearchCoverLetterList {
}