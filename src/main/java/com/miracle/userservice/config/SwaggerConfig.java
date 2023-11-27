package com.miracle.userservice.config;

import com.fasterxml.classmate.TypeResolver;
import com.miracle.userservice.controller.response.ErrorApiResponse;
import com.miracle.userservice.controller.response.SuccessApiResponse;
import com.miracle.userservice.util.Const;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.RequestParameterBuilder;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ParameterType;
import springfox.documentation.service.RequestParameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.ArrayList;
import java.util.List;

@EnableOpenApi
@Configuration
public class SwaggerConfig {

    private static final String BASE_PACKAGE = "com.miracle.userservice.controller.defaultpath";
    private static final String USER_PATH_BASE_PACKAGE = "com.miracle.userservice.controller.userpath";
    private static final String PATTERN = "/v1/**";
    private static final String USER_PATH_PATTERN = "/v1/user/{userId}/**";
    private static final String TITLE = "Miracle User Service API";
    private static final String DESCRIPTION = "유저와 연관된 요청들을 처리하는 API";
    private static final String VERSION = "1.0";

    @Bean
    public Docket docket(TypeResolver typeResolver) {
        return new Docket(DocumentationType.OAS_30)
                .globalRequestParameters(globalRequestParameterList())
                .additionalModels(
                        typeResolver.resolve(SuccessApiResponse.class),
                        typeResolver.resolve(ErrorApiResponse.class)
                )
                .useDefaultResponseMessages(false)
                .apiInfo(apiInfo())
                .groupName("default-path")
                .select()
                .apis(RequestHandlerSelectors.basePackage(BASE_PACKAGE))
                .paths(PathSelectors.ant(PATTERN))
                .build();
    }

    @Bean
    public Docket userPathDocket(TypeResolver typeResolver) {
        return new Docket(DocumentationType.OAS_30)
                .globalRequestParameters(userPathRequestParameterList())
                .additionalModels(
                        typeResolver.resolve(SuccessApiResponse.class),
                        typeResolver.resolve(ErrorApiResponse.class)
                )
                .useDefaultResponseMessages(false)
                .apiInfo(apiInfo())
                .groupName("user-path")
                .select()
                .apis(RequestHandlerSelectors.basePackage(USER_PATH_BASE_PACKAGE))
                .paths(PathSelectors.ant(USER_PATH_PATTERN))
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
        List<RequestParameter> requestParameterList = globalRequestParameterList();
        RequestParameter userId = new RequestParameterBuilder()
                .name(Const.RequestHeader.USER_ID)
                .in(ParameterType.HEADER)
                .required(Boolean.TRUE)
                .build();

        requestParameterList.add(userId);
        return requestParameterList;
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title(TITLE)
                .description(DESCRIPTION)
                .version(VERSION)
                .build();
    }
}
