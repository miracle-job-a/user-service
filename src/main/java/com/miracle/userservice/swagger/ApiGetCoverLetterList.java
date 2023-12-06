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
        summary = "자기소개서 목록 조회 처리",
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
                                                                  "id": 7,
                                                                  "title": "팀원 모두의 마음을 사로잡은 방법",
                                                                  "modifiedAt": "2023-12-06"
                                                              },
                                                              {
                                                                  "id": 2,
                                                                  "title": "성실함 : Blog 1일 1업로드 실천",
                                                                  "modifiedAt": "2023-12-02"
                                                              },
                                                              {
                                                                  "id": 3,
                                                                  "title": "비전공자의 커리어 전환기 : 야너두 할수있어",
                                                                  "modifiedAt": "2023-12-01"
                                                              },
                                                              {
                                                                  "id": 8,
                                                                  "title": "풀스택 개발자 성장기",
                                                                  "modifiedAt": "2023-11-06"
                                                              },
                                                              {
                                                                  "id": 1,
                                                                  "title": "한 달 만에 채용사이트를 구현할 수 있었던 비결",
                                                                  "modifiedAt": "2023-10-16"
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
                                        ),
                                        @ExampleObject(
                                                name = "정렬 파라미터 형식 오류",
                                                value = """
                                                        {
                                                            "httpStatus": 400,
                                                            "message": "정렬 파라미터 형식이 올바르지 않습니다. 값이 ('MODIFIED_AT_ASC', 'MODIFIE_AT_DESC') 중 하나여야 합니다.",
                                                            "code": "400_2",
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
public @interface ApiGetCoverLetterList {
}