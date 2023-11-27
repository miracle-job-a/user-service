package com.miracle.userservice.config;

import com.fasterxml.classmate.TypeResolver;
import com.miracle.userservice.controller.response.ErrorApiResponse;
import com.miracle.userservice.controller.response.SuccessApiResponse;
import com.miracle.userservice.swagger.DefaultPathDocket;
import com.miracle.userservice.swagger.UserPathDocket;
import com.miracle.userservice.util.Const;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import springfox.documentation.RequestHandler;
import springfox.documentation.builders.*;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ParameterType;
import springfox.documentation.service.RequestParameter;
import springfox.documentation.service.Response;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

@EnableOpenApi
@Configuration
public class SwaggerConfig {

    private static final String TITLE = "Miracle User Service API";
    private static final String DESCRIPTION = "유저와 연관된 요청들을 처리하는 API";
    private static final String VERSION = "1.0";
    private static final String BASE_PACKAGE = "com.miracle.userservice.controller";

    @Bean
    public Docket defaultPathdocket(TypeResolver typeResolver) {
        return baseDocket(typeResolver, "default-path")
                .select()
                .apis(withAnnotation(DefaultPathDocket.class))
                .build();
    }

    @Bean
    public Docket userPathDocket(TypeResolver typeResolver) {
        return baseDocket(typeResolver, "user-path")
                .globalRequestParameters(userPathRequestParameterList())
                .select()
                .apis(withAnnotation(UserPathDocket.class))
                .build();
    }

    private Docket baseDocket(TypeResolver typeResolver, String groupName) {
        return new Docket(DocumentationType.OAS_30)
                .globalRequestParameters(globalRequestParameterList())
                .globalResponses(HttpMethod.GET, globalResponse())
                .globalResponses(HttpMethod.POST, globalResponse())
                .globalResponses(HttpMethod.PUT, globalResponse())
                .globalResponses(HttpMethod.DELETE, globalResponse())
                .additionalModels(
                        typeResolver.resolve(SuccessApiResponse.class),
                        typeResolver.resolve(ErrorApiResponse.class)
                )
                .useDefaultResponseMessages(false)
                .apiInfo(apiInfo())
                .groupName(groupName)
                .select()
                .apis(RequestHandlerSelectors.basePackage(BASE_PACKAGE))
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title(TITLE)
                .description(DESCRIPTION)
                .version(VERSION)
                .build();
    }

    private List<RequestParameter> globalRequestParameterList() {
        List<RequestParameter> requestParameterList = new ArrayList<>();
        RequestParameter sessionId = new RequestParameterBuilder()
                .name(Const.RequestHeader.SESSION_ID)
                .in(ParameterType.HEADER)
                .required(Boolean.TRUE)
                .build();
        RequestParameter miracle = new RequestParameterBuilder()
                .name(Const.RequestHeader.MIRACLE)
                .in(ParameterType.HEADER)
                .required(Boolean.TRUE)
                .build();

        requestParameterList.add(sessionId);
        requestParameterList.add(miracle);
        return requestParameterList;
    }

    private List<RequestParameter> userPathRequestParameterList() {
        List<RequestParameter> requestParameterList = new ArrayList<>();
        RequestParameter userId = new RequestParameterBuilder()
                .name(Const.RequestHeader.USER_ID)
                .in(ParameterType.HEADER)
                .required(Boolean.TRUE)
                .build();

        requestParameterList.add(userId);
        return requestParameterList;
    }

    private List<Response> globalResponse() {
        List<Response> responseList = new ArrayList<>();
        Response response = new ResponseBuilder()
                .code("401")
                .description("비정상적인 요청")
                .isDefault(true)
                .examples(
                        List.of(new ExampleBuilder()
                                .id("1")
                                .mediaType("application/json")
                                .summary("토큰 인증 실패")
                                .value(new ErrorApiResponse(
                                        HttpStatus.UNAUTHORIZED.value(),
                                        "올바르지 않은 요청입니다.",
                                        "401",
                                        "InvalidRequestStateException"))
                                .build()
                        )
                )
                .build();

        responseList.add(response);
        return responseList;
    }

    private Predicate<RequestHandler> withAnnotation(Class<? extends Annotation> annotation) {
        Class<? extends Annotation> other = annotation.equals(DefaultPathDocket.class) ? UserPathDocket.class : DefaultPathDocket.class;
        return RequestHandlerSelectors.withMethodAnnotation(annotation)
                .or(
                        RequestHandlerSelectors.withMethodAnnotation(other).negate()
                                .and(RequestHandlerSelectors.withClassAnnotation(annotation))
                );
    }
}
